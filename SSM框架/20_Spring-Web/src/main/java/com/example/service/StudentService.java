package com.example.service;

import com.example.pojo.Student;
import java.util.List;

public interface StudentService {

	void addStudent(Student Student);

	List<Student> queryStudents();
}
