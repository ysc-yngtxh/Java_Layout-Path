package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.domain.Student;
import com.example.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    // @Reference是dubbo的注解，和@Autowired 和@Resource 一样都是表示注入。
    // 他一般注入的是分布式的远程服务的对象，用于标记这个服务具体使用了提供者的哪个接口实现，需要dubbo配置使用。
    @Reference(interfaceName = "com.example.service.StudentService", version="1.0.0", check=false)
    private StudentService studentService;

    @RequestMapping("/student/detail/{id}")
    public String studentDetail(Model model,
                                @PathVariable("id") Integer id){

        Student student = studentService.queryStudentById(id);

        model.addAttribute("student",student);
        return "studentDetail"; // 这个返回值是视图
    }

    @GetMapping("/student/all/count")
    public @ResponseBody Object allStudentCount(){
        Integer allStudentCount = studentService.queryAllStudentCount();
        return "学生总人数为："+allStudentCount;
    }
}
