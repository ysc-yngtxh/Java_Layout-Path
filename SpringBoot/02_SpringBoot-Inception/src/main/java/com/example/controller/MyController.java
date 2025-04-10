package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 */
@Controller
public class MyController {

    @RequestMapping("/say")
    public @ResponseBody String say() {
        return "Hello SpringBoot!";
    }
}
