package com.example.controller;

import com.example.exception.MyUserException;
import com.example.exception.custom.AgeException;
import com.example.exception.custom.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 */
@Controller
public class StudentController {

	@RequestMapping(value = "/some.do")
	public ModelAndView doSome(
            @RequestParam("name") String name,
            @RequestParam(value = "age", required = false) Integer age) throws MyUserException {
		ModelAndView mv = new ModelAndView();
		// 根据请求参数抛出异常
		if (!"zs".equals(name)) {
			throw new NameException("姓名不正确！！！");
		}
		if (age == null || age > 80) {
			throw new AgeException("年龄可能为null或者比较大！！！");
		}
		mv.addObject("myName", name);
		mv.addObject("myAge", age);
		mv.setViewName("show");
		return mv;
	}
}
