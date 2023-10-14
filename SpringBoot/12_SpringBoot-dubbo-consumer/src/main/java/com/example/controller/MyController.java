package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 */
@Controller
public class MyController {

    @Reference(interfaceClass = StudentService.class,version = "1.0.0",check = false)
    //@Reference注解就是用于标记这个服务具体使用了提供者的哪个接口实现
    private StudentService studentService;

    @RequestMapping(value="/student/count")
    public @ResponseBody Object query1(){

        Integer allStudedntCount = studentService.quetyAllStudedntCount("count");
        return "学生总人数为："+allStudedntCount;
    }
}
