package com.example.controller;

import com.example.domain.Student;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URISyntaxException;
import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/studentMap")
    public @ResponseBody Map<String, Student> queryStudentByIdMap(Integer id){
        Map<String, Student> student = studentService.queryStudentByIdMap(id);
        // String emails = student.get("游诗成").getEmail();     // 这样获取 email 会报错
        Map<String,String> email = (Map) student.get("游诗成");  // 这才是正确获取的写法
        email.get("email");
        return student;
    }

    @RequestMapping("/student")
    public @ResponseBody Map<String, Student> queryStudentByIdStudent(Integer id) {
        Map<String, Student> map = studentService.queryStudentByIdStudent(id);
        String email = map.get("游诗成").getEmail();
        return map;
    }

    @RequestMapping("/streamingQuery")
    public @ResponseBody void streamingQuery() {
        studentService.streamingQuery();
    }

    @RequestMapping("/csvResultHandler")
    public @ResponseBody void CsvResultHandler() throws URISyntaxException {
        // 调用Mapper方法，开始流式查询
        studentService.selectStudent();
    }
}
