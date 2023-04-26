package com.example.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youshicheng
 * @desc TODO 登陆后接口
 * @date 2023-04-07 上午10:14
 */

@RestController
public class SayHelloController {

    @RequestMapping("/sayHello")
    public String hello(){
        return "十年生死两茫茫，不思量，自难忘";
    }


    public static void main(String[] args) {
        // 使用BcryptPasswordEncoder加密
        BCryptPasswordEncoder bcyencoder = new BCryptPasswordEncoder();
        System.out.println(bcyencoder.encode("123456"));
    }

}
