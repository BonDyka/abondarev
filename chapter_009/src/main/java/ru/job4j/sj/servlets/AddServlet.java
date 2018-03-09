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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Servlet for adding user.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 09.03.2018.
 */
public class AddServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddServlet.class);

    private final IStorage store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append("<!DOCTYPE html>")
          .append("<html><head><title>Add user</title></head></body>")
          .append("<h1 align='center'>Add user form</h1>")
          .append("<div align='center'><form action='").append(req.getContextPath()).append("/add' method='post'>")
          .append("Name <input type='text' name='name'><br/>")
          .append("Login<input type='text' name='login'><br/>")
          .append("Email<input type='text' name='email'><br/>")
          .append("<input type='submit'>")
          .append("</form>")
          .append("</body></html>");

        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        if (name != null && login != null && email != null) {
            this.store.add(new User(name, login, email, new Timestamp(System.currentTimeMillis())));
            LOG.info("Request on add user to the store.");
        }
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
