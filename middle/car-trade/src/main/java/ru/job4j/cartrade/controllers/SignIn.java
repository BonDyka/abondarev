package ru.job4j.cartrade.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ru.job4j.carstore.models.annotated.User;
import ru.job4j.carstore.persistence.GenericDao;
import ru.job4j.carstore.persistence.criterias.UserByLogin;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pswd");
        User loginUser = new User();
        loginUser.setLogin(login);

        // The method readByCriteria with criteria UserByLogin returns list with single User entity.
        List<User> dbAnswer = new GenericDao<>(User.class).readByCriteria(new UserByLogin(loginUser));

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (dbAnswer.isEmpty() || !password.equals(dbAnswer.get(0).getPassword())) {
            JsonObject msgJson = new JsonObject();
            msgJson.addProperty("msg", "Incorrect login or password!");
            writer.append(msgJson.toString());
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            User result = dbAnswer.get(0);
            result.setPassword(null);
            writer.append(new Gson().toJson(result));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        resp.setContentType("application/json");
        writer.flush();
        writer.close();
    }

}
