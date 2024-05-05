package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 */
@Slf4j  // 用于打印日志和设置日志级别
@RestController
@RequestMapping("/user")
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")  // method方法为get,且采用了RESTful风格
    public User query(@PathVariable("id") Integer id){
        return userService.queryById(id);
    }
}
