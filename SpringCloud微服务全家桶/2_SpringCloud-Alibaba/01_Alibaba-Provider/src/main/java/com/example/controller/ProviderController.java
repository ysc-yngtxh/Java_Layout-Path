package com.example.controller;

import com.example.pojo.User;
import com.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public User query(@PathVariable("id") Integer id){
        return userService.queryById(id);
    }
}
