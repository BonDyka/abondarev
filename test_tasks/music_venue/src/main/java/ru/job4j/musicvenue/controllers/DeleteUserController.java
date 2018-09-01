package ru.job4j.musicvenue.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.musicvenue.domains.User;
import ru.job4j.musicvenue.persistence.dao.DaoFactory;
import ru.job4j.musicvenue.persistence.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserController extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteUserController.class);
    private static final DaoFactory FACTORY = DaoFactory.getFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.valueOf(req.getParameter("user_id"));
        UserDao uDao = FACTORY.getUserDao();
        try {
            User user = uDao.getById(userId);
            FACTORY.getAddressDao().delete(user.getAddress().getId());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        //TODO: answer to client
    }
}
