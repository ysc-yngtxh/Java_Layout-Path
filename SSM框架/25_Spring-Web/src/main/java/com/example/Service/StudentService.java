package com.example.Service;

import com.example.domain.Student;

import java.util.List;

public interface StudentService {

    void addStudent(Student student);

    List<Student> queryStudents();
}
