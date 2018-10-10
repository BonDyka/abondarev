package ru.job4j.sj.servlets;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.sj.store.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 31.03.2018.
 */
public class JsonPlaceController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(JsonPlaceController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserStore store = UserStore.getInstance();
        String places = req.getParameter("places");
        String jsonStr;

        if (places.equals("country")) {
            jsonStr = mapToJsonArray(store.getCountries());
        } else {
            int countryId = Integer.parseInt(req.getParameter("country_id"));
            jsonStr = mapToJsonArray(store.getCitiesOfCountry(countryId));
        }
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonStr);
        writer.flush();
    }

    private String mapToJsonArray(Map<Integer, String> map) {
        StringBuilder builder = new StringBuilder("[");
        for (Map.Entry<Integer, String> set : map.entrySet()) {
            builder.append(String.format("{\"id\": \"%s\", \"name\": \"%s\"},", set.getKey(), set.getValue()));
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("]");
        return builder.toString();
    }
}
