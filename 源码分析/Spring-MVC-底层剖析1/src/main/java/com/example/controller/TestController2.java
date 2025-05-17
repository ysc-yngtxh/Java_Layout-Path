package com.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-28 23:50:00
 * @apiNote TODO
 */
@Component("/test2")
public class TestController2 implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("传入数据 id 值为：" + request.getParameter("id"));
		ModelAndView modelAndView = new ModelAndView("test");
		return modelAndView;
	}

}
