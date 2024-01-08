package com.example.controller;

import com.example.service.SentinelMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @Autowired
    private SentinelMethodService userService;

    @GetMapping("/getUserById")
    public String getUserById(@RequestParam Integer id){
        return userService.selectUserById(id);
    }
}