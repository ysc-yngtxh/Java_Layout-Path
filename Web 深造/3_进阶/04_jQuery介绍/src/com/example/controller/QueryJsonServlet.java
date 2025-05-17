package com.example.controller;

import com.example.dao.ProvinceDao;
import com.example.entity.Province;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryJsonServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 默认值，{}：表示json格式的数据
		String json = "{}";

		// 获取请求参数，省份的id
		String strProId = request.getParameter("proid");

		// 判断proid有值时，调用dao查询数据
		if (strProId != null && strProId.trim().length() > 0) {
			ProvinceDao dao = new ProvinceDao();
			Province p = dao.queryProvinceById(Integer.valueOf(strProId));
			// 需要使用Jackson把Province对象转为json
			ObjectMapper om = new ObjectMapper();
			json = om.writeValueAsString(p);
		}

		// 把获取的数据，通过网络传给AJAX中的异步对象，响应结果数据
		// 指定服务器端返回给浏览器的是json格式的数据
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		// 输出数据---会赋给Ajax中responseText属性
		pw.println(json);
		pw.flush();
		pw.close();
	}

}
