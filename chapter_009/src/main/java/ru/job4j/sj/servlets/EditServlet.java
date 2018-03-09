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
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet for changing user info.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 08.03.2018.
 */
public class EditServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(EditServlet.class);

    private final IStorage store = UserStore.getInstance();

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        User user = login == null ? null : this.store.get(login);
        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        if (user != null) {
            pw.append("<!DOCTYPE html>")
                    .append("<html><head><title>Add user</title></head></body>")
                    .append("<h1 align='center'>Add user form</h1>")
                    .append("<div align='center'><form action='").append(req.getContextPath())
                    .append("/edit' method='post'>")
                    .append("Name <input type='text' name='name' value='").append(user.getName())
                    .append("' disabled><br/>")
                    .append("<input type='text' name='name' value='").append(user.getName())
                    .append("' hidden><br/>")
                    .append("Login<input type='text' name='login' value='").append(user.getLogin())
                    .append("' disabled><br/>")
                    .append("<input type='text' name='login' value='").append(user.getLogin())
                    .append("' hidden><br/>")
                    .append("Email<input type='text' name='email' value='").append(user.getEmail()).append("'><br/>")
                    .append("Created<input type='text' name='create' value='").append(user.getCreateDate().toString())
                    .append("' disabled><br/>")
                    .append("<input type='text' name='create' value='").append(user.getCreateDate().toString())
                    .append("' hidden><br/>")
                    .append("<input type='submit'>")
                    .append("</form>")
                    .append("</body></html>");
            pw.flush();
            LOG.info("Request on edit user info.");
        } else {
            LOG.info("User not found.");
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String createDate = req.getParameter("create");

        if (name != null && login != null && email != null && createDate != null) {
            try {
                this.store.edit(new User(name, login, email, new Timestamp(format.parse(createDate).getTime())));
            } catch (ParseException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
