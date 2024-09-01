package com.example.controller;

import com.example.dao.QuestionDao;
import com.example.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionFindByIdServlet extends HttpServlet {
    private static final long serialVersionUID = 2841989774475654655L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String questionId;
        QuestionDao dao = new QuestionDao();

        // 1、调用请求头里的请求参数
        questionId = request.getParameter("questionId");
        // 2、将请求头里的请求参数传到【根据试题编号查询试题信息】
        Question question = dao.findById(questionId);
        // 3、调用question_Update.jsp将试题信息写入到响应体
        request.setAttribute("key", question);
        request.getRequestDispatcher("/question_Update.jsp").forward(request, response);

    }
}
