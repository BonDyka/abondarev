package ru.job4j.sj.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.models.Role;
import ru.job4j.sj.models.User;
import ru.job4j.sj.store.IStorage;
import ru.job4j.sj.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Servlet for adding user.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 09.03.2018.
 */
public class AddUserController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddUserController.class);

    private final IStorage store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", this.store.getRoles());
        req.getRequestDispatcher("/WEB-INF/views/AddUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Role role = this.store.getRole(Integer.parseInt(req.getParameter("role_id")));

        if (name != null && login != null && password != null && email != null && role != null) {
            this.store.add(new User(name, login, password, email, new Timestamp(System.currentTimeMillis()), role));
            LOG.info("Request on add user to the store.");
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
