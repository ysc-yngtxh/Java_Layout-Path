package com.example.controller;

import com.example.domain.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/student")
    public @ResponseBody Map<String, Student> student(Integer id){
        Map<String, Student> student = studentService.queryStudentById(id);
        String email = student.get("游诗成").getEmail();
        return student;
    }

    @RequestMapping("/studentMap")
    public @ResponseBody Map<String, Student> studentMap(Integer id){
        Map<String, Student> student = studentService.queryStudentByIdMap(id);
        // String email = student.get("游诗成").getEmail();  会报错
        Map<String,String> email = (Map) student.get("游诗成");
        email.get("email");
        return student;
    }
}
