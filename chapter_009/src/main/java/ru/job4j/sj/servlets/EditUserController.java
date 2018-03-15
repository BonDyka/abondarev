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
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet for changing user info.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 08.03.2018.
 */
public class EditUserController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(EditUserController.class);

    private final IStorage store = UserStore.getInstance();

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", this.store.get(req.getParameter("login")));
        User currentUser = this.store.get((String) req.getSession(false).getAttribute("login"));
        req.setAttribute("roles", this.store.getRoles());
        req.setAttribute("currentUser", currentUser);
        req.getRequestDispatcher("/WEB-INF/views/EditUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String createDate = req.getParameter("create");
        Role role = this.store.getRole(Integer.parseInt(req.getParameter("role_id")));

        if (name != null && login != null && email != null && createDate != null) {
            try {
                this.store.edit(new User(name, login, password, email,
                        new Timestamp(format.parse(createDate).getTime()), role));
            } catch (ParseException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
