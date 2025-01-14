package com.example.service;

import com.example.mapper.StudentMapper;
import com.example.pojo.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentMapper studentMapper;

    // 使用set注入，赋值
    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public int addStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public List<Student> selectStudents() {
        return studentMapper.selectStudents();
    }
}
