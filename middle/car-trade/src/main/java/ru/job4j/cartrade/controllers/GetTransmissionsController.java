package ru.job4j.cartrade.controllers;


import com.google.gson.Gson;
import ru.job4j.carstore.models.annotated.Transmission;
import ru.job4j.carstore.persistence.GenericDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetTransmissionsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());

        List<Transmission> transmissions = new GenericDao<>(Transmission.class).readAll();

        Gson gson = new Gson();

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        printWriter.append(gson.toJson(transmissions)).flush();
    }
}
