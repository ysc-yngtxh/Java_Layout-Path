package com.example.dubbo.controller;

import com.example.dubbo.domain.User;
import com.example.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserService userService2;

    @RequestMapping("/index")
    public String userDetail(Model model,Integer id,String username){

        User user = userService.queryUserById(id, username);
        User user2 = userService2.queryUserById(id, username);

        model.addAttribute("user",user);
        model.addAttribute("user2",user2);

        return "index";
    }
}


