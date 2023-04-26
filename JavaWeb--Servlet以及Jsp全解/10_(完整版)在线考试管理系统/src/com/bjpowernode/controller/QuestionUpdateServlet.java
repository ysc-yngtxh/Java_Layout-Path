package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QuestionUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer questionId;
        String title,optionA,optionB,optionC,optionD,answer;
        QuestionDao dao = new QuestionDao();
        int result = 0;

        //1、取出更新试题后请求头参数
        questionId = Integer.valueOf(request.getParameter("questionId"));
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        Question question = new Question(questionId,title,optionA,optionB,optionC,optionD,answer);

        //2、利用JDBC技术，将更新后的数据写入数据库
        result = dao.update(question);

        //3、调用info.jsp将操作结果写入到响应体
        if(result == 1){
            request.setAttribute("info","试题更新成功");
        }else{
            request.setAttribute("info","试题更新失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
