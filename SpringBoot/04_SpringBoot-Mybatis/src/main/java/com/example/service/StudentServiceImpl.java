package com.example.service;

import com.example.domain.Student;
import com.example.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public Student queryStudentById(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}
}
