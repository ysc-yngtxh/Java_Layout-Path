package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 这个@RefreshScope 是Spring Cloud中的一个注解，用来实现Bean中属性的动态刷新。
 *  使用 @RefreshScope 注解的会生成一个代理对象，当属性发生变更的时候，代理对象会将原先的属性Bean清除，
 *  然后重新创建Bean，代理对象会从重新创建的Bean中获取属性数据。
 */
@RefreshScope
@RestController
public class ConfigRefreshController {

    @Value("${my.args.int}")
    private int i;

    @Value("${my.args.str}")
    private String str;

    @RequestMapping(value = "/test")
    public String test(){
        return "测试控制类！i = " + i + ";str = " + str;
    }
}
