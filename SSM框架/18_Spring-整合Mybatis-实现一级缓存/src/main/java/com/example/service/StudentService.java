package com.example.service;

import com.example.pojo.Student;
import java.util.List;

public interface StudentService {

	int addStudent(Student student);

	List<Student> selectStudents();
}
