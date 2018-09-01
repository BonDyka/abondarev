package ru.job4j.musicvenue.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.musicvenue.domains.User;
import ru.job4j.musicvenue.persistence.PersistException;
import ru.job4j.musicvenue.persistence.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class EditUserController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(EditUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String currentUserLogin = (String) session.getAttribute("login");
        String currentUserRole = (String) session.getAttribute("role");
        int userId = Integer.valueOf(req.getParameter("id"));
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();
        try {
            DaoFactory factory = DaoFactory.getFactory();
            User editUser = factory.getUserDao().getById(userId);
            if (!editUser.getLogin().equals(currentUserLogin)) {
                editUser.setPassword(null);
            }
            jsonObject.addProperty("editUser", gson.toJson(editUser));
            jsonObject.addProperty("currentUserRole", currentUserRole);
        } catch (PersistException e) {
            LOG.error(e.getMessage(), e);
        }
        resp.setContentType("application/json");

        new PrintWriter(resp.getOutputStream()).append(jsonObject.toString()).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
