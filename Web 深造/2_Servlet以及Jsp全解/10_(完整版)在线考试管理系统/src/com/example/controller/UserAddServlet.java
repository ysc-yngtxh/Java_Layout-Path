package com.example.controller;

import com.example.dao.UserDao;
import com.example.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class UserAddServlet extends HttpServlet {
    private static final long serialVersionUID = -3921769198819138521L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    // Tomcat负责销毁【请求对象】和【响应对象】
    // Tomcat负责将HTTP相应协议包推送到发起请求的浏览器上
    // 浏览器根据响应头content-type指定编译器对响应体二进制内容编辑
    // 浏览器将编译后结果在窗口中展示给用户【结束】
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName, password, sex, email;
        UserDao dao = new UserDao();
        Users user = null;
        int result = 0;

        // 1、【调用请求对象】读取【请求头】参数信息，得到用户的信息
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        sex = request.getParameter("sex");
        email = request.getParameter("email");

        // 2、【调用UserDao】将用户信息填充到insert命令并借助JDBC规范发送数据库服务器
        user = new Users(null, userName, password, sex, email);
        Date startDate = new Date();
        result = dao.add(user, request);
        Date endDate = new Date();
        System.out.println("添加消耗时间=" + (endDate.getTime()-startDate.getTime()) + "毫秒");

        // 3、调用info.jsp将操作结果写入到响应体
        if(result == 1) {
            request.setAttribute("info", "用户信息注册成功");
        } else {
            request.setAttribute("info", "用户信息注册失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
