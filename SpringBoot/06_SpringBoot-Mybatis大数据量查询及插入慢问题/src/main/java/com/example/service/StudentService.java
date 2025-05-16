package com.example.service;

import com.example.domain.Student;
import java.util.Map;

public interface StudentService {

	Map<String, Object> queryByIdBackMap(Integer id);

	Map<String, Student> queryByPrimaryKeyMap(Integer id);

	Student queryByIdBackStudent(Integer id);

	Map<String, Student> queryByPrimaryKeyStudent(Integer id);
}
