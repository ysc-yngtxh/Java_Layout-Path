package com.example.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-18 15:00
 * @apiNote TODO 实现Spring Cloud Gateway的路由以及过滤规则
 */
@RestController
public class GatewayController {

    @RequestMapping("/test")
    public String test() {
        return "Hello World.";
    }

    // 降级服务接口1
    @RequestMapping("/fallback1")
    public String fallbackHandler() {
        return "This is the Gateway Fallback1.";
    }

    // 降级服务接口2
    @RequestMapping("/fallback2")
    public String fallbackHandler2() {
        return "This is the Gateway Fallback2.";
    }
}
