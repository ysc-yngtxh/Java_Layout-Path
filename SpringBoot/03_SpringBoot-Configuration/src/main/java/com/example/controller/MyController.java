package com.example.controller;

import com.example.domain.Abc;
import com.example.domain.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 */
@Controller
public class MyController {

	@Autowired  // Spring注入
	private School school;

	@Autowired  // Spring注入
	private Abc abc;

	@RequestMapping(value = "/say")
	public @ResponseBody String say() {
		return "school.name=" + school.getName() + "-----school.webSit=" + school.getWebSit();
	}

	// TODO 返回视图是Jsp文件
	//      ⚠️：使用Jsp文件：1、需要配置视图解析器 2、需要在Pom文件中提供编译Jsp文件的配置
	@RequestMapping(value = "/solo")
	public ModelAndView solo() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hello，SpringBoot");
		mv.setViewName("solo");
		return mv;
	}

	@RequestMapping(value = "/index")
	public String index(Model model) {
		model.addAttribute("message", "Hello World");
		// 返回值是一个是视图
		return "solo";
	}
}
