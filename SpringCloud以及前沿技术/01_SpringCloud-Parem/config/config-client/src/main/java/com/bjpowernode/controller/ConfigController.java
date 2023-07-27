package com.bjpowernode.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope  // 通知Spring容器，热刷新的时候，重新刷新当前类型对应的所有对象
// Spring容器为提升热刷新效率，默认不刷新对象内容，只有明确指定的对象，才刷新
@RestController
public class ConfigController {

    @Value("${my.args.int}")
    private int i;
    @Value("${my.args.str}")
    private String str;

    @RequestMapping("/test")
    public String test(){
        return "测试控制类！i="+i+";str="+str;
    }
}
