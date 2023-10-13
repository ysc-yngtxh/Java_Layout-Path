package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigBusController {

    @Value("${my.args.int}")
    private int i;

    @Value("${my.args.str}")
    private String str;

    @RequestMapping(value = "/test")
    public String test(){
        return "测试控制类！i = " + i + ";str = " + str;
    }
}
