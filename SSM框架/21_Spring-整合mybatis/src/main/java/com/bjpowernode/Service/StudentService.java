package com.bjpowernode.Service;

import com.bjpowernode.domain.Student;

import java.util.List;

public interface StudentService {

    int addStudent(Student student);
    List<Student> queryStudents();
}
