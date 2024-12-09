package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-17 08:07
 * @apiNote TODO 控制层
 */
@Controller
public class InMemoryController {

    @RequestMapping("/admin")
    public String UserAdmin(){
        return "admin";
    }

    @GetMapping(value = "/manager")
    public String UserManager(){
        return "manager";
    }
}
