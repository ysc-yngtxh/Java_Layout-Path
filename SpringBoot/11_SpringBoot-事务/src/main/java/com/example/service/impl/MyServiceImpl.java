package com.example.service.impl;

import com.example.domain.Student;
import com.example.mapper.StudentMapper;
import com.example.service.MyService;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

	// TODO 使用构造方法注入
	private final StudentMapper studentMapper;

	public MyServiceImpl(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}

	@Override
	public int queryStudentById(Student student) {
		int i = studentMapper.updateByPrimaryKeySelective(student);
		return i;
	}

	public Student query(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}
}
