package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = request.getServletContext();
        HttpSession session = request.getSession();

        application.setAttribute("sid", 10);
        session.setAttribute("sname", "mike");
        request.setAttribute("home", "新起屯");

        request.getRequestDispatcher("/index_01.jsp").forward(request,response);
    }
}
