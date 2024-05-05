package com.example.dao;

import com.example.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {

    List<Student> selectStudents();

    List<Student> selectStudentsParam(String name, Integer age);

    List<Student> selectStudentsAnnotationParam(@Param("name") String name, @Param("age") Integer age);

    List<Student> selectStudentsReflect(String name, Integer age);

    int insertStudent(Student student);
}
