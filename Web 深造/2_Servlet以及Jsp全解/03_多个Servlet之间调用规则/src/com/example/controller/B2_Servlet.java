package com.example.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class B2_Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 1、通过【请求对象】向Tomcat索要当前网站中【全局作用域对象】
		ServletContext application = request.getServletContext();
		// 2、从全局作用域对象的到指定关键字对应数据
		Integer money = (Integer) application.getAttribute("key1");
		System.out.println(money);
		// 为什么选用Integer，而不选用int？因为可能会发生空指针异常，而包装类Integer就不存在这种问题
	}

}
