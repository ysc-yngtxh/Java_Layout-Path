package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,money;
        // 1、调用请求对象读取【请求头】参数信息
        userName = request.getParameter("userName");
        money = request.getParameter("money");
        // 2、开卡
        Cookie card1 = new Cookie("userName", userName);
        Cookie card2 = new Cookie("money", money);
        /*
          指定card2在客户端硬盘上存活一分钟
          card2.setMaxAge(60);
         */
        // 3、发卡，将Cookie写入到响应头交给浏览器
        response.addCookie(card1);
        response.addCookie(card2);
        // 4、通知浏览器将【点餐页面】内容写入到响应体交给浏览器
        request.getRequestDispatcher("/index_2.html").forward(request, response);
    }
}
