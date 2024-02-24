package com.example.controller;

import com.example.domain.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 */
@Controller
public class StudenController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/student")
    public @ResponseBody Object student(Integer id){
        Student student = studentService.queryStudentById(id);
        return student;
    }

    // 这里体现了Mybatis的一级缓存
    @RequestMapping("/cache")
    public @ResponseBody Object cache(Integer id){
        Student student = studentService.queryStudentByIdCache(id);
        return student;
    }
}
