package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: 用户接口
 * @date 2022/07/30 20:53
 */
@RestController
public class UserController {

    @RequestMapping("/sayHello")
    public String Hello() {
        return "hello world!";
    }

    @RequestMapping("/test")
    public String test() {
        return "Hello World";
    }
}
