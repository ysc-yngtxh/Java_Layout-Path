package com.example.controller;

import com.example.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 游家纨绔
 */
@Slf4j  // 用于打印日志和设置日志级别
@RestController
@RequestMapping("/user")
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    // 直接通过路径访问，固定的Ip、端口
    @GetMapping("/{id}")
    public User query(@PathVariable("id") Integer id){
        return restTemplate.getForObject("http://localhost:8081/user/"+id, User.class);
    }

    // 动态Ip、端口
    @GetMapping("/template/{id}")
    public User queryTemplate(@PathVariable("id") Integer id){
        return restTemplate.getForObject("http://nacos-provider/user/"+id, User.class);
    }
}
