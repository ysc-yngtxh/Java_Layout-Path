package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 游家纨绔
 *
 * 使用Ajax的局部刷新
 *
 * 1、新建jsp，使用XMLHttpRequest异步对象。
 *    使用异步对象有四个步骤
 *      1)创建
 *      2)绑定事件
 *      3)初始请求
 *      4)发送请求
 *
 * 2、创建服务器的servlet，接收并处理数据，把数据输出给异步对象
 */
public class BmiAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("发起了请求，局部刷新");

        // 接收参数
        String strName = request.getParameter("name");
        String weight = request.getParameter("w");
        String height = request.getParameter("h");

        // 计算bmi
        float w = Float.valueOf(weight);
        float h = Float.valueOf(height);
        float bmi = w/(h*h);

        // 判断bmi的范围
        String msg = "";
        if(bmi <= 18.5) {
            msg = "您比较瘦";
        } else if(bmi > 18.5 && bmi <= 23.9) {
            msg = "你的bmi是正常的";
        } else if(bmi >24 && bmi <=27) {
            msg = "你的身体比较胖";
        } else {
            msg = "您的身体肥胖";
        }
        System.out.println("msg=" + msg);
        msg = "您好:" + strName + "先生/女士，您的bmi值是:" + bmi + "," + msg;

        // 响应Ajax需要的数据，使用HttpServletResponse输出数据
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(msg);
        pw.flush();
        pw.close();
    }
}
