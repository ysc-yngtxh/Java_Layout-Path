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
public class MyController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/put")
    public @ResponseBody Object query(String key, String value) {
        studentService.put(key, value);
        return "值已成功放入redis";
    }

    @RequestMapping(value="/get")
    public @ResponseBody Object query1(String key, String value) {
        String count = studentService.get("count");
        return "数据count为：" + count;
    }
}
