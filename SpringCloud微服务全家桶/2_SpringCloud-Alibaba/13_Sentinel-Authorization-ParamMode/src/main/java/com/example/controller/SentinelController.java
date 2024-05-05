package com.example.controller;

import com.example.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SentinelController {

    @Autowired
    private SentinelService sentinelService;

    // 定义了授权规则的接口 /getHandler
    @GetMapping("/getHandler")
    @ResponseBody
    public String getHandler() {
        return sentinelService.getHandler();
    }

    // 定义了热点规则的接口 /getList
    @GetMapping("/getList")
    @ResponseBody
    public String getList(Integer id, String username) {
        return sentinelService.getList(id, username);
    }
}