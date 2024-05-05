package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 12:52
 * @apiNote TODO
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return "你好哦！！！";
    }
}
