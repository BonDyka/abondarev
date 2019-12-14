package ru.job4j.cartrade.controllers;

import com.google.gson.Gson;
import ru.job4j.carstore.models.annotated.Announcement;
import ru.job4j.carstore.models.annotated.Photo;
import ru.job4j.carstore.persistence.AnnouncementDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowAnnouncements extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Announcement> list = new AnnouncementDao().readAll();
        for (Announcement anno : list) {
            for (Photo photo : anno.getPhotos()) {
                photo.setAnnouncement(null);
            }
        }
        Gson gson = new Gson();

        PrintWriter pw = new PrintWriter(resp.getOutputStream());
        pw.append(gson.toJson(list));
        pw.flush();
        pw.close();
    }
}
