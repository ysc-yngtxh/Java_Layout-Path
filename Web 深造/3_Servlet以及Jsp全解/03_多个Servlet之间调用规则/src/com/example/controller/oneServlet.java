package com.example.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 游家纨绔
 */
public class oneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("OneServlet 负责 洗韭菜");
        // 重定向解决方案
        response.sendRedirect("/myWeb/two");

        // System.out.println("OneServlet 实施 麻醉");
        // 请求转发解决方案
        // RequestDispatcher report= request.getRequestDispatcher("/two");  // 不加上服务器名称
        // report.forward(request,response);
    }
}
