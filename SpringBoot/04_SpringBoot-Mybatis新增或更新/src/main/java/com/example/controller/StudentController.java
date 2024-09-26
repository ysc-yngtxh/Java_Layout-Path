package com.example.controller;

import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 这里体现了Mybatis的一级缓存
    @RequestMapping("/cache")
    public @ResponseBody Object cache(Integer id){
        return studentService.queryStudentByIdCacheL1(id);
    }
}
