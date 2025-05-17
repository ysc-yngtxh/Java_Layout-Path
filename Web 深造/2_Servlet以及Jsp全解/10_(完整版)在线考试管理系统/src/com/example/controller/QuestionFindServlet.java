package com.example.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionFindServlet extends HttpServlet {
	private static final long serialVersionUID = -5993010568857954026L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/question_Find.jsp").forward(request, response);
	}

}
