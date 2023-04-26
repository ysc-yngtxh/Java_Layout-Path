package com.example.controller;


import com.example.domain.ResponseResult;
import com.example.pojo.User;
import com.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/07/30 23:42
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/user/index")
    public ModelAndView Hello(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;

    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public void login(@RequestBody User user){
        System.out.println("login");
    }

    @RequestMapping(value = "/toMain")
    public ModelAndView toMain(){
        Map<Object, Object> login = loginService.login();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        modelAndView.addObject("token", login.get("token"));
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ResponseResult<Void> logout(){
        // 登出
        return loginService.logout();
    }
}
