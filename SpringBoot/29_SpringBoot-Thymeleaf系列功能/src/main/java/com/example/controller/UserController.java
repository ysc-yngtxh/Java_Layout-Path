package com.example.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 */
@Controller
public class UserController {

	// 条件判断
	@RequestMapping("/condition")
	public String query(Model model) {
		model.addAttribute("sex", 1);
		model.addAttribute("flag", true);
		model.addAttribute("productType", 0);
		return "condition";
	}

	// 内敛表达式
	@RequestMapping("/inline")
	public String query1(Model model) {
		model.addAttribute("data", "SpringBoot inline");
		return "inline";
	}

	// 字符串拼接
	@RequestMapping("/splice")
	public String query2(Model model) {
		model.addAttribute("totalRows", 120);
		model.addAttribute("totalPage", 12);
		model.addAttribute("currentPage", 1);
		return "splice";
	}

	// 基本表达式对象
	@RequestMapping("/tindex")
	public String query3(HttpServletRequest request, Model model) {
		model.addAttribute("username", "lisi");
		request.getSession().setAttribute("data", "sessionData");
		return "tindex";
	}

	// 功能表达式对象
	@RequestMapping("/values")
	public String query4(Model model) {
		model.addAttribute("time", new Date());
		model.addAttribute("data", "SpringBoot Hello World");
		return "values";
	}
}
