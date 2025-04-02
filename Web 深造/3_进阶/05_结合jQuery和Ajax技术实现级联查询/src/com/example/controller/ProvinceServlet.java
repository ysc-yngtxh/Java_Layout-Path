package com.example.controller;

import com.example.dao.dao;
import com.example.entity.Province;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        dao dao = new dao();
        List<Province> list = dao.provinceList();
        String json = "{}";

        if(list != null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(list);
        }

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(json);
        pw.flush();
        pw.close();
    }
}
