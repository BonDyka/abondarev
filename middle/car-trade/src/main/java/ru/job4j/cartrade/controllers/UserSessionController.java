package ru.job4j.cartrade.controllers;

import com.google.gson.JsonObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserSessionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        resp.setContentType("application/json");
        if (session != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            JsonObject answer = new JsonObject();
            answer.addProperty("login", (String) session.getAttribute("ulogin"));
            PrintWriter pw = new PrintWriter(resp.getOutputStream());
            pw.append(answer.toString()).flush();
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
