package com.leverx.animals.servlet;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.leverx.animals.exception.AutoIdException;
import com.leverx.animals.exception.IncorrectRequestIdException;
import com.leverx.animals.exception.NotFoundException;
import com.leverx.animals.exception.UpdatingIdException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.*;

@WebServlet("/errorHandler")
public class ErrorHandler extends HttpServlet {

    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String TEXT_CONTENT_TYPE = "text/html";

    private Gson gson;

    @Override
    public void init() {
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        RuntimeException exc = (RuntimeException) req.getAttribute("javax.servlet.error.exception");
        processException(req, resp, exc);
    }

    private void processException(HttpServletRequest req, HttpServletResponse resp,
                                  RuntimeException exc) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        if (exc instanceof IncorrectRequestIdException) {
            processIncorrectRequestIdException(req, resp);
        } else if (exc instanceof AutoIdException) {
            processAutoIdException(req, resp);
        } else if (exc instanceof NotFoundException) {
            processNotFoundException(resp, (NotFoundException) exc);
        } else {
            processUpdatingIdException(resp, (UpdatingIdException) exc);
        }
    }

    private void processUpdatingIdException(HttpServletResponse resp, UpdatingIdException exc)
            throws IOException {
        sendResponse(resp, SC_CONFLICT, TEXT_CONTENT_TYPE, exc.getMessage());
    }

    private void processAutoIdException(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sendResponse(resp, SC_CONFLICT, JSON_CONTENT_TYPE, CharStreams.toString(req.getReader()));
    }

    private void processNotFoundException(HttpServletResponse resp, NotFoundException exc)
            throws IOException {
        sendResponse(resp, SC_NOT_FOUND, TEXT_CONTENT_TYPE,exc.getMessage());
    }

    private void processIncorrectRequestIdException(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        sendResponse(resp, SC_BAD_REQUEST, JSON_CONTENT_TYPE, gson.toJson(req.getParameterMap()));
    }

    private void sendResponse(HttpServletResponse resp, int status, String contentType, String msg)
            throws IOException {
        resp.setContentType(contentType);
        resp.setStatus(status);
        PrintWriter out = resp.getWriter();
        out.write(msg);
        out.flush();
    }
}