package com.harmreduction;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorld extends HttpServlet {

    /**
     * A simple HelloWorld Servlet
     */
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws java.io.IOException {
        res.setContentType("text/html");
        res.getWriter().write("Hello World!");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws java.io.IOException {
        doPost(req, res);
    }
}