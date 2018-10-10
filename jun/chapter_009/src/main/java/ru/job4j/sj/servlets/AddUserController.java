package ru.job4j.sj.servlets;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.models.Role;
import ru.job4j.sj.models.User;
import ru.job4j.sj.store.NotCompleteOperationException;
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
public class AddUserController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddUserController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore store = UserStore.getInstance();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        Role role = store.getRole(2);
        JsonObject json = new JsonObject();

        try {
            store.add(new User(name, login, password, email, store.getCountries().get(Integer.parseInt(country)),
                 store.getCitiesOfCountry(Integer.parseInt(city)).get(Integer.parseInt(city)),
                 new Timestamp(System.currentTimeMillis()), role
            ));
            json.addProperty("created", true);
        } catch (NotCompleteOperationException e) {
            LOG.error(e.getMessage(), e);
            resp.setStatus(208); // code 208 means that the data already was send.
            json.addProperty("created", false);
        }

        LOG.info("Request on add user to the store.");
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(json.toString());
        writer.flush();
    }
}
