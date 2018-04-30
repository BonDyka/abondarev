package ru.job4j.sj.servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.models.User;
import ru.job4j.sj.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Start servlet for CRUD app.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 03.03.2018.
 */
public class UsersViewController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UsersViewController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(String.format("%s/user.html", req.getContextPath())).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore store = UserStore.getInstance();
        String currentUserLogin = null;
        HttpSession session = req.getSession(false);
        if (session != null) {
            currentUserLogin = (String) session.getAttribute("login");
        }
        User currentUser = store.get(currentUserLogin);

        List<User> users = store.getAll();
        Gson gson = new Gson();
        String jsonUser = gson.toJson(currentUser);
        String jsonList = gson.toJson(users);
        JsonObject json = new JsonObject();
        json.addProperty("currentUser", jsonUser);
        json.addProperty("listOfUsers", jsonList);

        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json.toString());
        writer.flush();
    }
}
