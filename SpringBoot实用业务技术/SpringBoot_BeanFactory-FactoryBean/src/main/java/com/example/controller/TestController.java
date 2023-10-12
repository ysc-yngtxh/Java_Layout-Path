package com.example.controller;

import com.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;
   
    @GetMapping("/doService")
    public void doService() {
        testService.doService();
    }
}
