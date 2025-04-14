package com.example.controller;

import com.example.pojo.JwtToken;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/{id}")
    public JwtToken register(@PathVariable("id") Integer id) {
        User user = userService.queryById(id);
        return new JwtToken().success("我能得到返回数据吗？", user);
    }

    @RequestMapping("/login/{id}")
    public User query(@PathVariable("id") Integer id) {
        User user = userService.queryById(id);
        return user;
    }
}
