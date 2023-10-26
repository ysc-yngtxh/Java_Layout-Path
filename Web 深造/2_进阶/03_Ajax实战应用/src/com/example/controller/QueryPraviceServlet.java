package com.example.controller;

import com.example.dao.ProvinceDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QueryPraviceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strProid = request.getParameter("proid");
        System.out.println("strProid:"+strProid);

        String name = "";
        if(strProid != null && !"".equals(strProid.trim())){
            ProvinceDao dao = new ProvinceDao();
            name = dao.queryproviceNameById(Integer.valueOf(strProid));
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(name);
        pw.flush();
        pw.close();
    }
}
