package com.example.controller;

import com.example.dao.ProvinceDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryPraviceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String strProId = request.getParameter("proid");
        System.out.println("strProId:"+strProId);

        String name = "";
        if(strProId != null && !strProId.trim().isEmpty()){
            ProvinceDao dao = new ProvinceDao();
            name = dao.queryProvinceNameById(Integer.valueOf(strProId));
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(name);
        pw.flush();
        pw.close();
    }
}
