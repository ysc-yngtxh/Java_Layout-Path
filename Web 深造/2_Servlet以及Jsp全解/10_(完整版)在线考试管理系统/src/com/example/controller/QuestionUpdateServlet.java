package com.example.controller;

import com.example.dao.QuestionDao;
import com.example.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1642115984976104101L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer questionId;
        String title,optionA,optionB,optionC,optionD,answer;
        QuestionDao dao = new QuestionDao();
        int result = 0;

        // 1、取出更新试题后请求头参数
        questionId = Integer.valueOf(request.getParameter("questionId"));
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        Question question = new Question(questionId, title, optionA, optionB, optionC, optionD, answer);

        // 2、利用JDBC技术，将更新后的数据写入数据库
        result = dao.update(question);

        // 3、调用info.jsp将操作结果写入到响应体
        if(result == 1) {
            request.setAttribute("info", "试题更新成功");
        } else {
            request.setAttribute("info", "试题更新失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }
}
