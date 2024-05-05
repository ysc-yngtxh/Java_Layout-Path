package com.example.dao;

import com.example.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentDao {

    List<Student> selectStudents();

    List<Student> selectStudentsParam(Integer age, String name);

    int insertStudent(Student student);
}
