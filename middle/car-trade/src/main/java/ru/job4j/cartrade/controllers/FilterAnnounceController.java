package ru.job4j.cartrade.controllers;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.annotated.Announcement;
import ru.job4j.carstore.models.annotated.Photo;
import ru.job4j.carstore.persistence.AnnouncementDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class FilterAnnounceController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(FilterAnnounceController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();

        List<Announcement> announcements = new AnnouncementDao().filterAnnouncement(params);
        for (Announcement anno : announcements) {
            for (Photo photo : anno.getPhotos()) {
                photo.setAnnouncement(null);
            }
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        Gson gson = new Gson();

        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(gson.toJson(announcements));
        pw.flush();
        pw.close();
    }
}
