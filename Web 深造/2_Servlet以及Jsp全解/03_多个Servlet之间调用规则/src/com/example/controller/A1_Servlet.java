package com.example.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 游家纨绔
 */
public class A1_Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 1、通过【请求对象】向Tomcat索要当前网站中【全局作用域对象】
		ServletContext application = request.getServletContext();
		// 2、将数据添加到全局作用域对象，作为共享数据
		application.setAttribute("key1", 100);
	}

}
