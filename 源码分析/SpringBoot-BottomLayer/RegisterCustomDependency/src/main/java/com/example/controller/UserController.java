package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-01 22:05
 * @apiNote TODO
 */
@RestController
public class UserController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
