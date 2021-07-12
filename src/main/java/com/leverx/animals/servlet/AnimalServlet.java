package com.leverx.animals.servlet;

import com.google.gson.Gson;
import com.leverx.animals.entity.Dog;
import com.leverx.animals.repository.AnimalRepository;
import com.leverx.animals.repository.AnimalRepositoryImpl;
import com.leverx.animals.repository.UserRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/animal")
public class AnimalServlet extends HttpServlet {
    private AnimalRepository animalRepository;

    @Override
    public void init() {
        animalRepository = new AnimalRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");

        resp.setCharacterEncoding("UTF-8");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
