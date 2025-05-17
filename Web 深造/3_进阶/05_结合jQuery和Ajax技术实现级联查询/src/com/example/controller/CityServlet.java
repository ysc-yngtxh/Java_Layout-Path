package com.example.controller;

import com.example.dao.dao;
import com.example.entity.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CityServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String json = "{}";
		String strProvinceId = request.getParameter("proid");
		dao dao = new dao();
		List<City> citylist = dao.cityList(Integer.valueOf(strProvinceId));

		response.setContentType("application/json;charset=utf-8");
		if (citylist != null) {
			ObjectMapper om = new ObjectMapper();
			json = om.writeValueAsString(citylist);
		}

		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

}
