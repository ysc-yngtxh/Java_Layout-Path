package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 游家纨绔
 */
public class BmiServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接受请求参数
		String strName = request.getParameter("name");
		String height = request.getParameter("h");
		String weight = request.getParameter("w");
		System.out.println(strName);

		// 计算bmi:  bmi = 体重/身高的平方
		float h = Float.parseFloat(height);
		float w = Float.parseFloat(weight);
		float bmi = w / (h * h);

		// 判断bmi的范围
		String msg = "";
		if (bmi <= 18.5) {
			msg = "您比较瘦";
		} else if (bmi > 18.5 && bmi <= 23.9) {
			msg = "你的bmi是正常的";
		} else if (bmi > 24 && bmi <= 27) {
			msg = "你的身体比较胖";
		} else {
			msg = "您的身体肥胖";
		}
		System.out.println("msg=" + msg);
		msg = "您好:" + strName + "先生/女士，您的bmi值是:" + bmi + "," + msg;

		// 把数据存入到request(利用请求转发做输出)
		// request.setAttribute("msg", msg);
		// 转发到新的页面
		// request.getRequestDispatcher("/result.jsp").forward(request, response);

		// 使用HttpServletResponse输出数据(利用响应体直接做输出流)
		response.setContentType("text/html;charset=utf-8");
		// 获取PrintWriter
		PrintWriter pw = response.getWriter();
		// 输出数据
		pw.println(msg);
		// 清空缓存
		pw.flush();
		// 关闭close
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
