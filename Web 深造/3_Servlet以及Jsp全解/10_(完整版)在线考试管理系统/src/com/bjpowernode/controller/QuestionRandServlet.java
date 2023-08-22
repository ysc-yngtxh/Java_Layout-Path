package com.bjpowernode.controller;

import com.bjpowernode.dao.QuestionDao;
import com.bjpowernode.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionRandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        QuestionDao dao = new QuestionDao();
        HttpSession session = request.getSession(false);
        //1、调用Dao对象随机从question表中拿出4道题目
        List<Question> ques = dao.rand();
        //2、将4道题目添加到request作为共享数据
        session.setAttribute("key",ques);
        //3、请求转发
        request.getRequestDispatcher("/question_Rand.jsp").forward(request,response);
    }
}
