package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uriGlobal = request.getRequestURI();
        String[] uris = uriGlobal.split("\\+");
        OutputStream outputStream = response.getOutputStream();
        boolean first = true;
        for (String uri : uris){
            File file = new File(getServletContext().getRealPath(".") , Paths.get("../../src/main/webapp/static/", uri).toString());
            if (!file.exists()){
                file = new File(getServletContext().getRealPath("/static/" + uri));
            }
            if (file.isFile()) {
                if (first){
                    response.setContentType(getContentTypeFromName(uri));
                    first = false;
                }

                Files.copy(file.toPath(), outputStream);
                outputStream.flush();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }

    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
