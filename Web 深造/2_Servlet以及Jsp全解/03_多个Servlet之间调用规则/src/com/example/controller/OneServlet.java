package com.example.controller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 游家纨绔
 */
public class OneServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("OneServlet 负责 洗韭菜");
		// 重定向解决方案
		response.sendRedirect("/myWeb/two");

		// System.out.println("OneServlet 实施 麻醉");
		// 请求转发解决方案
		// RequestDispatcher report= request.getRequestDispatcher("/two");  // 不加上服务器名称
		// report.forward(request,response);
	}

}
