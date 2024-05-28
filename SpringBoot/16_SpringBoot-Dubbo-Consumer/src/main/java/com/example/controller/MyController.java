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

    // @Reference是dubbo的注解，和@Autowired 和@Resource 一样都是表示注入。
    // 他一般注入的是分布式的远程服务的对象，用于标记这个服务具体使用了提供者的哪个接口实现，需要dubbo配置使用。
    @Reference(interfaceClass = StudentService.class, version = "1.0.0", check = false)
    private StudentService studentService;

    @RequestMapping(value="/student/count")
    public @ResponseBody Object query1(){

        Integer allStudentCount = studentService.queryAllStudentCount("count");
        return "学生总人数为：" + allStudentCount;
    }
}
