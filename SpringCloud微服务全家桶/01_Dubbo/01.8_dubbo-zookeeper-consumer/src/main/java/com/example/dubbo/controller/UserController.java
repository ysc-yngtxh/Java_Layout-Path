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

    @RequestMapping("/index")
    public String userDetail(Model model,Integer id,String username){
        User user = userService.queryUserById(id,username);
        model.addAttribute("user",user);
        return "index";
    }
}

/**
 * 把提供者和消费者的Tomcat服务打开后，再把注册中心zookeeper打开
 *    (在D盘下-->zookeeper-->apache-zookeeper-3.6.2-bin-->bin-->zkServer.cmd)
 *
 * 然后再到浏览器上输入url
 */