package com.example.controller;

import com.example.domain.Student;
import com.example.service.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    // TODO 使用Setter方式注入
    private MyService myService;

    // @Autowired
    // TODO 参考Bean生命周期过程：实例化、属性注入、初始化、Bean的使用、Bean的销毁
    //  Setter注入通常在Bean的属性上进行依赖注入，它们在Bean的实例化之后才被调用。
    //  这里直接提供setter方法是不会被Bean加载调用的，也无法完成其属性注入的能力，因此这里的 myService 为空。
    //  通过是在setter方法上添加 @Autowired 注解，利用Jdk的反射机制，通过执行setter方法的方式[method.invoke()]，
    //  将属性赋值到已经实例完成的Bean中，从而完成属性注入。
    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    @RequestMapping("/student")
    public @ResponseBody Object student(Integer id, String name) {
        Student student1 = new Student();
        student1.setId(id);
        student1.setName(name);
        int updateCount = myService.queryStudentById(student1);
        return "修改学生编号" + id + "的姓名修改结果" + updateCount;
    }

    @RequestMapping("/students")
    public @ResponseBody Student student(Integer id) {
        Student query = myService.query(id);
        return query;
    }
}
