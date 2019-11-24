package ru.job4j.cartrade.controllers;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ShowPhotoController extends HttpServlet {
    private static  final String BASE_DIR = "C:/upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().replace("/photos", "");

        File file = new File(BASE_DIR + path);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = fis.readAllBytes();

        resp.setStatus(HttpServletResponse.SC_OK);
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}
