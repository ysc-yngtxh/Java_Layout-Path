package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-09 19:34
 * @apiNote TODO
 */
@Controller
public class TestController {

    @RequestMapping("/query")
    public @ResponseBody String loader() {
        return "access success!!!";
    }
}
