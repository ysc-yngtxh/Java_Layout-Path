package com.example.controller;

import com.example.openfeign.EchoOpenFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelFeignController {

    @Autowired
    private EchoOpenFeign echoOpenFeign;

    // 自定义URL资源异常处理逻辑
    @GetMapping("/customException")
    public String customException() {
        return "自定义Sentinel的异常处理逻辑！！！";
    }

    // Sentinel集成OpenFeign
    @GetMapping("/sentinelFeign/{id}")
    public String sentinelFeignById(@PathVariable Integer id) {
        return echoOpenFeign.echo(id);
    }
}
