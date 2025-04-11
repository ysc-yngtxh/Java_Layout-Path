package com.example.service;

import com.example.domain.Student;
import com.example.mapper.StudentMapper;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	public Map<String, Object> queryByIdBackMap(Integer id) {
		return studentMapper.selectByIdBackMap(id);
	}

	@Override
	public Map<String, Student> queryByPrimaryKeyMap(Integer id) {
		return studentMapper.selectByPrimaryKeyMap(id);
	}

	@Override
	public Student queryByIdBackStudent(Integer id) {
		return studentMapper.selectByIdBackStudent(id);
	}

	@Override
	public Map<String, Student> queryByPrimaryKeyStudent(Integer id) {
		return studentMapper.selectByPrimaryKeyStudent(id);
	}
}
