package com.example.service;

import com.example.domain.Student;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultHandler;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface StudentService {

    Map<String, Student> queryStudentByIdMap(Integer id);

    Map<String, Student> queryStudentByIdStudent(Integer id);

    // List<Student> simpleQuery();

    void streamingQuery();

    Cursor<Student> cursorQuery();

    void selectStudent();
}
