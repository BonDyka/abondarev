package ru.job4j.sj.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.store.IStorage;
import ru.job4j.sj.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete user servlet
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 08.03.2018.
 */
public class DeleteUserController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteUserController.class);

    private final IStorage store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        if (login != null) {
            this.store.delete(login);
            LOG.info(String.format("Request on deleting user %s from the store", login));
        }
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }
}
