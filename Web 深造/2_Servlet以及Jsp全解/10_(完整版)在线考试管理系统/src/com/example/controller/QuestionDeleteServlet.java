package com.example.controller;

import com.example.dao.QuestionDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = -6187211210478081173L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String questionId;
		QuestionDao dao = new QuestionDao();
		int result = 0;
		PrintWriter out = null;
		// 1、调用请求对象读取请求头参数用户编号
		questionId = request.getParameter("questionId");
		// 2、调用Dao将用户编号填充到delete命令并发送到数据库服务器
		result = dao.delete(questionId);
		// 3、调用响应对象将处理结果以二进制写入到响应体，交给浏览器
		response.setContentType("text/html;charset=utf-8");
		out = response.getWriter();
		if (result == 1) {
			out.print("<font style='color:red;font-size:40'>试题信息删除成功</font>");
		} else {
			out.print("<font style='color:red;font-size:40'>试题信息删除失败</font>");
		}
	}

}
