package com.example.controller;

import com.example.service.SentinelClassService;
import com.example.service.SentinelMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @Autowired
    private SentinelMethodService methodService;

    @Autowired
    private SentinelClassService classService;

    @GetMapping("/getMethodById")
    public String getMethodById(@RequestParam Integer id){
        return methodService.selectMethodById(id);
    }

    @GetMapping("/getClassById")
    public String getClassById(@RequestParam Integer id){
        return classService.selectClassById(id);
    }
}