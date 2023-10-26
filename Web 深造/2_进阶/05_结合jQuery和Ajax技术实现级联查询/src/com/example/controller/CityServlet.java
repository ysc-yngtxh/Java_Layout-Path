package com.example.controller;

import com.example.dao.dao;
import com.example.entity.City;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        String strProvinceId = request.getParameter("proid");
        dao dao = new dao();
        List<City> citylist = dao.cityList(Integer.valueOf(strProvinceId));

        response.setContentType("application/json;charset=utf-8");
        if(citylist != null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(citylist);
        }

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
