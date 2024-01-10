package com.example.controller;

import com.example.service.SentinelClassService;
import com.example.service.SentinelMethodService;
import com.example.service.DefaultHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SentinelController {

    private final DefaultHandlerService defaultHandlerService;

    private final SentinelMethodService methodService;

    private final SentinelClassService classService;

    // 默认的Sentinel流控异常处理
    @GetMapping("/getDefaultHandler")
    public String getDefaultHandler(@RequestParam Integer id){
        return defaultHandlerService.defaultBlockRule(id);
    }


    // 基于Method方式的限流、熔断降级写法
    @GetMapping("/getMethodById")
    public String getMethodById(@RequestParam Integer id){
        return methodService.selectMethodById(id);
    }


    // 基于Class方式的限流、熔断降级写法
    @GetMapping("/getClassById")
    public String getClassById(@RequestParam Integer id){
        return classService.selectClassById(id);
    }
}