package com.example.controller;

import com.example.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @Autowired
    private SentinelService sentinelService;

    // 定义了授权规则的接口 /getHandler
    @GetMapping("/getHandler")
    public String getHandler() {
        return sentinelService.getHandler();
    }

    // 定义了热点规则的接口 /getList
    @GetMapping("/getList")
    public String getList(Integer id, String username) {
        return sentinelService.getList(id, username);
    }
}
