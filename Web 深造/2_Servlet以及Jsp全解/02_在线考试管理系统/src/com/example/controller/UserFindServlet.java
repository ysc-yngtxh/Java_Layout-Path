package com.example.controller;

import com.example.dao.UserDao;
import com.example.entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFindServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDao dao = new UserDao();
		PrintWriter out;

		// 1、【调用Dao】将查询命令推送到数据库服务器上，得到所有用户信息【List】
		List<Users> userList = dao.findAll();
		// 2、【调用响应对象】将用户信息结合<table>标签命令以二进制形式写入到响应体
		response.setContentType("text/html;charset=utf-8");
		out = response.getWriter();
		out.print("<table style='border: '1'; align='center''>");
		out.print("<tr>");
		out.print("<td>用户编号</td>");
		out.print("<td>用户姓名</td>");
		out.print("<td>用户密码</td>");
		out.print("<td>用户性别</td>");
		out.print("<td align='center'>用户邮箱</td>");
		out.print("<td>操作</td>");
		out.print("</tr>");
		for (Users users : userList) {
			out.print("<tr>");
			out.print("<td>" + users.getUserId() + "</td>");
			out.print("<td>" + users.getUserName() + "</td>");
			out.print("<td>******</td>");
			out.print("<td>" + users.getSex() + "</td>");
			out.print("<td>" + users.getEmail() + "</td>");
			out.print("<td><a href='/myWeb/user/delete?userId=" + users.getUserId() + "'>删除用户</a></td>");
			out.print("</tr>");
		}
		out.print("</table>");
		// 使用out.print();过多增加程序员开发难度，后面学习的JSP会来解决这个问题
	}

}
