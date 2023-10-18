package com.example.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-18 15:02
 * @apiNote TODO 实现Spring Cloud Gateway的路由以及过滤规则
 */
@RestController
public class GatewayController {

    // 降级服务接口
    @RequestMapping("/fallback")
    public String fallbackHandler() {
        return "This is the Gateway Fallback.";
    }
}
