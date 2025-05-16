package com.example.service;

import com.example.domain.Student;

public interface StudentService {

	// 根据学生id查询详情
	Student queryStudentById(Integer id);
}
