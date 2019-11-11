package ru.job4j.cartrade.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.annotated.User;
import ru.job4j.carstore.persistence.GenericDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SignUp extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SignUp.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pswd");
        String fName = req.getParameter("fname");
        String lName = req.getParameter("lname");
        String phone = req.getParameter("phone");

        resp.setContentType("application/json");
        JsonObject answer = new JsonObject();


        if (!login.isEmpty() && !password.isEmpty() && !fName.isEmpty() && !lName.isEmpty() && !phone.isEmpty()) {
            User newUser = new User(login, password, fName, lName, phone);
            try {
                new GenericDao<>(User.class).saveOrUpdate(newUser);
                resp.setStatus(HttpServletResponse.SC_OK);
                answer.addProperty("msg", "User created! You can sign in!");
            } catch (Exception e) {
                LOG.error("Bad trying for create user.", e);
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                answer.addProperty("msg", "User already exists!");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            answer.addProperty("msg", "Fields can't be empty!");
        }
        new PrintWriter(resp.getOutputStream()).append(answer.toString()).flush();
    }
}
