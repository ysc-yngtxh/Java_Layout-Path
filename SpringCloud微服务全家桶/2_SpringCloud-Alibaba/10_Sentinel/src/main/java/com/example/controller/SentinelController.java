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

    // 默认的Sentinel流控异常处理。
    // 接口"/getDefaultHandler"默认被定义为 Sentinel的资源，属于URL限流。
    // 但这资源不属于Controller层，而是属于Web资源(DispatcherServlet)
    @GetMapping("/getDefaultHandler")
    public String getDefaultHandler(){
        return defaultHandlerService.defaultBlockRule();
    }


    // 默认的Sentinel流控异常处理。
    // 接口"/getDefaultHandler"默认被定义为 Sentinel的资源，属于URL限流。
    // 但这资源不属于Controller层，而是属于Web资源(DispatcherServlet)
    @GetMapping("/getDefinitionBlockRule")
    public String getDefinitionBlockRule(){
        return defaultHandlerService.definitionBlockRule();
    }


    // 基于Method方式的限流、熔断降级写法。
    // 接口"/getDefaultHandler"默认被定义为 Sentinel的资源，属于URL限流。
    // 但这资源不属于Controller层，而是属于Web资源(DispatcherServlet)
    @GetMapping("/getMethodById")
    public String getMethodById(@RequestParam Integer id){
        return methodService.selectMethodById(id);
    }


    // 基于Class方式的限流、熔断降级写法。
    // 接口"/getClassById"已经被定义为 Sentinel的资源，但这资源不属于Controller，而是属于Web资源(DispatcherServlet)
    @GetMapping("/getClassById")
    public String getClassById(@RequestParam Integer id){
        return classService.selectClassById(id);
    }
}