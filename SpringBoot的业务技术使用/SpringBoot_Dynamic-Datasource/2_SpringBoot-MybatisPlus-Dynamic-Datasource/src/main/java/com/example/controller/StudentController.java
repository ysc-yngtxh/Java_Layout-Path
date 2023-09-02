package com.example.controller;

import com.example.entity.Student;
import com.example.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (Student)表控制层
 *
 * @author 游家纨绔
 * @since 2023-09-02 21:59:40
 */
@RestController
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentServiceImpl studentService;

    @RequestMapping("/findByIds")
    public List<Student> findByIds() {
        return studentService.findByMasterIds();
    }
}

