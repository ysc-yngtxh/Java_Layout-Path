package com.example.controller;

import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 */
@Controller
@Slf4j
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/student/count")
	public @ResponseBody String studentCount() {

		/* log方法级别从低到高 */
		log.trace("查询当前学生总人数");
		log.debug("查询当前学生总人数");
		log.info("查询当前学生总人数");
		log.warn("查询当前学生总人数");
		log.error("查询当前学生总人数");

		// SpringBoot默认是INFO，因此低于INFO的TRACE和DEBUG都不会输出

		Integer studentCount = studentService.queryStudentCount();

		return "学生总人数为：" + studentCount;
	}
}
