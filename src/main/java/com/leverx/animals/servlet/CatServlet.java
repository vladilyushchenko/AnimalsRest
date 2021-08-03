package com.leverx.animals.servlet;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.leverx.animals.service.AnimalService;
import com.leverx.animals.service.AnimalServiceImpl;
import com.leverx.animals.util.ResponseSender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.leverx.animals.servlet.ResponseConstants.JSON_CONTENT_TYPE;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@WebServlet("/cats")
public class CatServlet extends HttpServlet {

    private AnimalService animalService;
    private Gson gson;
    private ResponseSender responseSender;

    @Override
    public void init() {
        animalService = new AnimalServiceImpl();
        gson = new Gson();
        responseSender = new ResponseSender();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cat cat = gson.fromJson(CharStreams.toString(req.getReader()), Cat.class);
        responseSender.sendResponse(resp, SC_OK, JSON_CONTENT_TYPE, gson.toJson(animalService.create(cat)));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cat cat = gson.fromJson(CharStreams.toString(req.getReader()), Cat.class);
        responseSender.sendResponse(resp, SC_OK, JSON_CONTENT_TYPE, gson.toJson(animalService.update(cat)));
    }
}
