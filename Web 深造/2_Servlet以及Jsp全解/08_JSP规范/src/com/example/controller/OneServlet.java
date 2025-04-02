package com.example.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ServletContext application = request.getServletContext();
        String value = (String)application.getAttribute("key1");
        System.out.println("value = " + value);
    }
}
