package ru.job4j.sj.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Delete user servlet
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 08.03.2018.
 */
public class DeleteUserController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String nextPage = String.format("%s/view", req.getContextPath());
        if (session != null) {
            String currentUserLogin = (String) session.getAttribute("login");
            String login = req.getParameter("login");
            if (login != null) {
                UserStore.getInstance().delete(login);
                LOG.info(String.format("Request on deleting user %s from the store", login));
            }
            if (currentUserLogin.equals(login)) {
                nextPage = "/";
                session.invalidate();
            }
        }
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(nextPage);
        writer.flush();

    }
}
