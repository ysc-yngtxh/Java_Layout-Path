package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: 用户权限访问接口
 * @date 2022/07/30 20:53
 */
@Controller
public class UserController {

    @RequestMapping("/message")
    // 权限注解，即：访问这个接口前需要当前用户有'message'权限
    @PreAuthorize("hasAuthority('message')")
    public @ResponseBody String test(){
        return "Hello World";
    }

    @RequestMapping("/spel")
    // 这里使用的是SpringEl表达式，简称spel表达式
    @PreAuthorize("@spel.hasAuthoriza()")
    public @ResponseBody String role(){
        return "无丝竹之乱耳，无案牍之劳形;";
    }
}