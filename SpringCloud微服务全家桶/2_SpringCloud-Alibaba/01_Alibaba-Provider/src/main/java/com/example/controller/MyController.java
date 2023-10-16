package com.example.controller;

import com.example.pojo.User;
import com.example.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author 游家纨绔
 */
@RestController
@RequestMapping("/provider")
public class MyController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public User query(@PathVariable("id") Integer id){
        return userService.queryById(id);
    }
}
