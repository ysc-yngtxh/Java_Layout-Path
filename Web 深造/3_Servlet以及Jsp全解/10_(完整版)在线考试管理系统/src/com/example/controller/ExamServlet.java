package com.example.controller;

import com.example.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ExamServlet extends HttpServlet {
    private static final long serialVersionUID = 4173009197070830169L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        // getSession方法有参与无参有区别的，有参可以通过session过滤器的拦截。
        List<Question> list = null;
        int fraction = 0;

        // 1、取出session私人储物柜里的四道题
        list = (List)session.getAttribute("key");
        // 2、将四道题进行遍历
        for (Question question : list) {
            Integer questionId = question.getQuestionId();
            String answer = question.getAnswer();
            String userAnswer = request.getParameter("answer_" + questionId);
            // 3、判分
            if(userAnswer.equals(answer)){
                fraction += 25;
            }
        }
        // 4、将分数写入到request中，作为共享数据
        request.setAttribute("info", "本次考试成绩:" + fraction);
        // 5、请求转发将分数写入转发体
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }
}
