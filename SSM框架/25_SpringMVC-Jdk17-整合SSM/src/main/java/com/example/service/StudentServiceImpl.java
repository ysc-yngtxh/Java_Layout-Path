package com.example.service;

import com.example.mapper.StudentDao;
import com.example.pojo.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 */
@Service
public class StudentServiceImpl implements StudentService {

	// 引用类型自动注入@Autowired,@Resource
	@Autowired
	private StudentDao studentDao;

	@Override
	public int addStudent(Student Student) {
		return studentDao.insertStudent(Student);
	}

	@Override
	public List<Student> findStudents() {
		return studentDao.selectStudents();
	}
}
