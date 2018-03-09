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
import java.util.List;

/**
 * Start servlet for CRUD app.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 03.03.2018.
 */
public class UsersServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UsersServlet.class);

    private final IStorage store = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = this.store.getAll();
        StringBuilder sb = new StringBuilder("<table cellspacing='2px' style='border:1px solid black'>");
        sb.append("<tr><th>Name</th><th>Login</th><th>Email</th><th>Create date</th><th>Actions<th></tr>");
        for (User user : users) {
            sb.append("<tr><td>").append(user.getName()).append("</td><td>").append(user.getLogin())
              .append("</td><td>").append(user.getEmail()).append("</td><td>").append(user.getCreateDate())
              .append("</td><td>")
              .append("<a href='").append(req.getContextPath()).append("/edit?login=").append(user.getLogin()).append("'>Edit</a><br/>")
              .append("<a href='").append(req.getContextPath()).append("/delete?login=").append(user.getLogin()).append("'>Delete</a>")
              .append("</td></tr>");
        }
        sb.append("</table>");

        resp.setContentType("text/html");
        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append("<!DOCTYPE html>")
          .append("<html>")
          .append("<head><title>User info storage</title></head>")
          .append("<body><div align='center'><h1>User Info Storage</h1></div>")
          .append("<div align='center'>")
          .append(sb.toString())
          .append("</div>")
          .append("<a href='").append(req.getContextPath()).append("/add'>Add user</add>")
          .append("</body></html>");

        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
