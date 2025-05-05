package com.example.自定义注解.controller;

import com.example.自定义注解.annotation.LoginRequired;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/12/1 12:52
 */
@Controller
@LoginRequired
public class LoginController {

    // 登录接口
    @RequestMapping(value="/login/{id}", produces="text/json;charset=utf-8")
    public @ResponseBody String login(@PathVariable String id, HttpServletRequest request) {
        request.getSession().setAttribute("login", id);
        return "登陆成功!!!";
    }

    // 排除拦截器的接口
    @RequestMapping(value="/login1", produces="text/json;charset=utf-8")
    public @ResponseBody String login1() {
        return "访问成功--login1";
    }

    // 被拦截其所拦截
    @RequestMapping(value="/login2", produces="text/json;charset=utf-8")
    public @ResponseBody String login2() {
        return "访问成功--login2";
    }

    // 自定义的注解(白名单接口)
    @LoginRequired
    @RequestMapping(value="/login3", produces="text/json;charset=utf-8")
    public @ResponseBody String login3() {
        return "访问成功--login3";
    }
}
