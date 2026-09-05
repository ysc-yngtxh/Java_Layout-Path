package com.example.mapper;

import com.example.pojo.Student;
import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentDao {

	int insertStudent(Student Student);

	List<Student> selectStudents();
}
