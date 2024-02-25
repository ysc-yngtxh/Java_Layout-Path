package com.example;

import com.example.domain.Student;
import com.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private StudentService studentService;
    @Test
    void contextLoads() {
        Student student = studentService.queryStudentByIdCacheL1(1);
    }
}
