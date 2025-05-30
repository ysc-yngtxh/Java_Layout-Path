package com.example.controller;

import java.util.Enumeration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 游家纨绔
 */
public class E5_Servlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 1、通过请求对象获得【请求头】中【所有请求参数名】
		Enumeration<?> paramNames = request.getParameterNames();  // 将所有请求参数名称保存到一个枚举对象进行方法返回
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			// 2、通过请求对象读取指定的请求参数的值
			String value = request.getParameter(paramName);
			System.out.println("请求参数名：" + paramName + " 请求参数值：" + value);
		}
	}

}
