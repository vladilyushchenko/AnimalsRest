package com.leverx.animals.servlet;

import com.google.gson.Gson;
import com.leverx.animals.exception.IncorrectRequestIdException;
import com.leverx.animals.service.UserService;
import com.leverx.animals.service.UserServiceImpl;
import com.leverx.animals.util.ResponseSender;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.leverx.animals.servlet.ResponseConstants.INCORRECT_ID_MESSAGE;
import static com.leverx.animals.servlet.ResponseConstants.JSON_CONTENT_TYPE;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserService userService;
    private ResponseSender responseSender;
    private Gson gson;

    @Override
    public void init() {
        userService = new UserServiceImpl();
        responseSender = new ResponseSender();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        responseSender.sendResponse(resp, SC_OK, JSON_CONTENT_TYPE,
                gson.toJson(userService.getById(getIdFromRequest(req))));
    }

    private long getIdFromRequest(HttpServletRequest req) {
        try {
            return Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new IncorrectRequestIdException(INCORRECT_ID_MESSAGE);
        }
    }
}
