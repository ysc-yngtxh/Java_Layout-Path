package com.example.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ServletContext application = request.getServletContext();
        application.setAttribute("key1",100); // 新增共享数据OneListener
        application.setAttribute("key1",200); // 更新共享数据
        application.removeAttribute("key1");     // 删除共享数据
    }
}
