package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 idea快捷功能：
 Run --> Edit Configurations --> Tomcat server --> server --> on "Update" action 和 on frame deactivation都选上Update classes and resources
 (表示的意思是动态资源和静态资源更新后会立即生效，不用再进行重启Tomcat。直接在浏览器界面进行刷新，可以实现资源的更新)
 */
public class B2Servlet extends HttpServlet {
    public B2Servlet() {
        System.out.println("B2Servlet类被创建实例对象");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("B2Servlet类针对浏览器发送Post请求方式处理");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("B2Servlet类针对浏览器发送Get请求方式处理");
    }
}
