package ru.job4j.sj.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.models.Role;
import ru.job4j.sj.models.User;
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

    SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore store = UserStore.getInstance();
        req.setAttribute("user", store.get(req.getParameter("login")));
        User currentUser = store.get((String) req.getSession(false).getAttribute("login"));
        req.setAttribute("roles", store.getRoles());
        req.setAttribute("currentUser", currentUser);
        req.getRequestDispatcher("/WEB-INF/views/EditUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore store = UserStore.getInstance();

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        int countryId = Integer.parseInt(req.getParameter("country"));
        String country = store.getCountries().get(countryId);
        int cityId = Integer.parseInt(req.getParameter("city"));
        String city = store.getCitiesOfCountry(countryId).get(cityId);
        Role role = store.getRole(Integer.parseInt(req.getParameter("role_id")));

        store.edit(new User(name, login, password, email, country, city, null, role));
    }
}
