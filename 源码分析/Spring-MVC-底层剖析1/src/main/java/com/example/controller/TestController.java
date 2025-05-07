package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-09 19:34
 * @apiNote TODO
 */
@RestController
public class TestController {

    @RequestMapping("/query")
    public String loader() {
        return "access success!!!";
    }
}
