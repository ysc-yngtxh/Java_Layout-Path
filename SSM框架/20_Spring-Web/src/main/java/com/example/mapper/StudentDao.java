package com.example.mapper;

import com.example.pojo.Student;
import java.util.List;

public interface StudentDao {

	int insertStudent(Student Student);

	List<Student> selectStudents();
}
