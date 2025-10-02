package com.example.controller;

import com.example.domain.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 */
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/student")
	public Student student(Integer id) {
		return studentService.queryStudentById(id);
	}
}
