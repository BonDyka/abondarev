package ru.job4j.sj.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.models.User;
import ru.job4j.sj.store.IStorage;
import ru.job4j.sj.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 11.03.2018.
 */
public class SignInController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SignInController.class);

    private final IStorage store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/SigninView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (!login.equals("") && !password.equals("")) {
            User user = this.store.get(login);
            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("login", user.getLogin());
                resp.sendRedirect(String.format("%s/", req.getContextPath()));
            } else {
                req.setAttribute("error", "Invalid login or password");
                doGet(req, resp);
            }
        } else {
            req.setAttribute("error", "You need fill all fields");
            doGet(req, resp);
        }
    }
}
