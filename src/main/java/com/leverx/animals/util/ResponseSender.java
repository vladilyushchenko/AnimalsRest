package com.leverx.animals.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.leverx.animals.servlet.ResponseConstants.UTF_ENCODING;

public class ResponseSender {

    public void sendResponse(HttpServletResponse resp, int status, String contentType, String msg)
            throws IOException {
        resp.setContentType(UTF_ENCODING);
        resp.setContentType(contentType);
        resp.setStatus(status);
        PrintWriter out = resp.getWriter();
        out.write(msg);
        out.flush();
    }
}
