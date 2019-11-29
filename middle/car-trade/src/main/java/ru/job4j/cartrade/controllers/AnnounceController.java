package ru.job4j.cartrade.controllers;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.carstore.models.annotated.*;
import ru.job4j.carstore.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnnounceController extends HttpServlet {
    private static final String BASE_UPLOAD_DIR = "C:/upload";

    private static final Logger LOG = LoggerFactory.getLogger(AnnounceController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(String.format("%s/pages/announce_form.html", req.getContextPath())).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String ulogin = (String) session.getAttribute("ulogin");
        Announcement announcement = new Announcement();
        announcement.setAuthor(new User());
        announcement.getAuthor().setId((Long) session.getAttribute("uId"));
        Car car = new Car();
        Engine engine = new Engine();
        CarBody body = new CarBody();
        Transmission trns = new Transmission();
        List<String> photoPaths = new ArrayList<>();

        if (ServletFileUpload.isMultipartContent(req)) {
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        if ("car-name".equals(item.getFieldName())) {
                            car.setName(item.getString());
                        } else if ("price".equals(item.getFieldName())) {
                            announcement.setPrice(Integer.parseInt(item.getString()));
                        } else if ("trns".equals(item.getFieldName())) {
                            trns.setId(Integer.parseInt(item.getString()));
                        } else if ("car-body".equals(item.getFieldName())) {
                            body.setId(Integer.parseInt(item.getString()));
                        } else if ("engine-type".equals(item.getFieldName())) {
                            engine.setType(item.getString());
                        } else if ("volume".equals(item.getFieldName())) {
                            engine.setVolume(Double.parseDouble(item.getString()));
                        } else if ("power".equals(item.getFieldName())) {
                            engine.setPower(Integer.parseInt(item.getString()));
                        }
                    } else {
                        photoPaths.add(processFile(item, ulogin));
                    }
                }
                new GenericDao<>(Engine.class).saveOrUpdate(engine);
                if (engine.getId() != 0) {
                    saveCar(car, body, trns, engine);
                }
                if (car.getId() != 0) {
                    announcement.setCar(car);
                    new GenericDao<>(Announcement.class).saveOrUpdate(announcement);
                }
                for (String item : photoPaths) {
                    Photo photo = new Photo(item);
                    photo.setAnnouncement(announcement);
                    new GenericDao<>(Photo.class).saveOrUpdate(photo);
                }
            } catch (Exception e) {
                LOG.error("It is not multipart request!", e);
            }
        }
    }

    private File uniqueUploadDirForUserRequest(String ulogin) {
        File file;
        do {
            Random random = new Random();
            String pathToUpload = String.format("%s/%s/%s", BASE_UPLOAD_DIR, ulogin, random.nextInt(100));
            file = new File(pathToUpload);
        } while (file.exists());
        file.mkdirs();
        return file;
    }

    private String processFile(FileItem item, String ulogin) throws Exception {
        File uploadDir = uniqueUploadDirForUserRequest(ulogin);
        String itemName = item.getName();
        String ext = itemName.substring(itemName.lastIndexOf("."));
        File uploadFile = new File(uploadDir, item.getFieldName() + ext);
        item.write(uploadFile);
        return uploadFile.getAbsolutePath().replace(BASE_UPLOAD_DIR, "");
    }

    private void saveCar(Car car, CarBody body, Transmission trns, Engine engine) {
        car.setBody(body);
        car.setTransmission(trns);
        car.setEngine(engine);
        new GenericDao<>(Car.class).saveOrUpdate(car);
    }
}
