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

    @RequestMapping("/map")
    public @ResponseBody Map<String, Object> queryByIdBackMap(Integer id) {
        Map<String, Object> student = studentService.queryByIdBackMap(id);
        Object name = student.get("name");
        return student;
    }

    @RequestMapping("/mapAndMapKey")
    public @ResponseBody Map<String, Student> queryStudentByIdMap(Integer id) {
        Map<String, Student> map = studentService.queryByPrimaryKeyMap(id);

        // String emails = map.get("游诗成").getEmail();      // 这样获取 email 会报错
        Map<String, String> email = (Map) map.get("秦岚");   // 这才是正确获取的写法
        System.out.println(email.get("email"));

        return map;
    }

    @RequestMapping("/student")
    public @ResponseBody Student selectByIdBackStudent(Integer id) {
        Student student = studentService.queryByIdBackStudent(id);
        student.setEmail(student.getEmail() + "⭐️⭐️⭐️⭐️⭐️");
        return student;
    }

    @RequestMapping("/studentAndMapKey")
    public @ResponseBody Map<String, Student> queryStudentByIdStudent(Integer id) {
        Map<String, Student> map = studentService.queryByPrimaryKeyStudent(id);

        String email = map.get("秦岚").getEmail();
        System.out.println(email);

        return map;
    }
}
