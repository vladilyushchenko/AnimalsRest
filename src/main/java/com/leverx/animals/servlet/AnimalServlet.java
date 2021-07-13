package com.leverx.animals.servlet;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.leverx.animals.entity.Animal;
import com.leverx.animals.entity.Dog;
import com.leverx.animals.exception.IncorrectRequestIdException;
import com.leverx.animals.service.AnimalService;
import com.leverx.animals.service.AnimalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/animals")
public class AnimalServlet extends HttpServlet {

    private static final String INCORRECT_ID_MESSAGE = "Please, write correct \"id\" parameter.";

    private AnimalService animalService;
    private Gson gson;

    @Override
    public void init() {
        animalService = new AnimalServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = getIdFromRequest(req);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(gson.toJson(animalService.getById(id)));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        super.doPost(req, resp);
        Animal animal = gson.fromJson(CharStreams.toString(req.getReader()), Dog.class);
        int stop = 0;
    }

    private long getIdFromRequest(HttpServletRequest req) {
        try {
            return Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new IncorrectRequestIdException(INCORRECT_ID_MESSAGE);
        }
    }

//    private Animal fromJson(String json) {
//
//    }

}
