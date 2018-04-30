package ru.job4j.sj.servlets;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.models.User;
import ru.job4j.sj.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for authorisation.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 11.03.2018.
 */
public class SignInController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SignInController.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String path = req.getContextPath();
        String next = String.format("%s/view", req.getContextPath());

        User user = UserStore.getInstance().get(login);
        JsonObject json = new JsonObject();
        boolean result = false;
        if (user != null && user.getPassword().equals(password)) {
            result = true;
            req.getSession().setAttribute("login", login);
        }
        json.addProperty("isValid", result);
        json.addProperty("nextPage", next);
        PrintWriter out = new PrintWriter(resp.getOutputStream());
        out.append(json.toString());
        out.flush();
    }
}
