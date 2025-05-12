package com.example.controller;

import com.example.openService.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SentinelFeignController {

    @Autowired
    private EchoService echoService;

    // 自定义URL资源异常处理逻辑
    @GetMapping("/customException")
    @ResponseBody
    public String customException() {
        return "自定义Sentinel的异常处理逻辑！！！";
    }

    // Sentinel集成OpenFeign
    @GetMapping("/sentinelFeign/{id}")
    @ResponseBody
    public String sentinelFeignById(@PathVariable Integer id) {
        return echoService.echo(id);
    }
}
