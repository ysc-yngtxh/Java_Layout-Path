package com.example.controller;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.example.pojo.User;
import com.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 这个@RefreshScope 是Spring Cloud中的一个注解，用来实现Bean中属性的动态刷新。
 *  使用 @RefreshScope 注解的会生成一个代理对象，当属性发生变更的时候，代理对象会将原先的属性Bean清除，
 *  然后重新创建Bean，代理对象会从重新创建的Bean中获取属性数据。
 */
@RefreshScope
@RestController
@RequestMapping("/config")
public class MyController {

    @Autowired
    private UserServiceImpl userService;

    // 为更好理解以下两个类，可参考：https://blog.csdn.net/RookiexiaoMu_a/article/details/125346337
    @Autowired
    private NacosConfigManager nacosConfigManager;
    @Autowired
    private NacosConfigProperties nacosConfigProperties;

    // 这里获取的是Nacos注册中心的配置文件中的属性值，当我们在Nacos的配置文件中修改属性值，这里会自动刷新为最新的值。
    // 必须和注解@RefreshScope搭配，才会实现自动刷新，数据一致。
    @Value("${nacos.config.username}")
    private String username;

    @GetMapping("/{id}")
    public User query(@PathVariable("id") Integer id){
        System.err.println("name -- " + username);
        return userService.queryById(id);
    }
}
