package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.com.bjpowernode.entity.Student;

public class TwoServlet extends HttpServlet {
    private static final long serialVersionUID = -1143759616744706403L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、创建一个引用类型实例对象
        Student stu = new Student(20, "allen");
        // 2、将引用类型对象存入到请求的作用域对象作为共享数据
        request.setAttribute("key", stu);
        // 3、请求转发，向Tomcat申请调用index_02.jsp
        request.getRequestDispatcher("/index_02.jsp").forward(request, response);
    }
}
