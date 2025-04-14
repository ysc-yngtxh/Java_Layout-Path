package com.example.controller;

import com.example.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 游家纨绔
 */
@Controller
@RequestMapping("/user")
public class  MyController {

    // 用户登陆的请求
    @RequestMapping("/login")
    public @ResponseBody Object login(HttpServletRequest request) {
        // 将用户的信息存放到session中
        User user = new User();
        user.setId(1001);
        user.setUserName("ZhangSan");
        request.getSession().setAttribute("user",user);
        return "LOGIN SUCCESS";
    }

    // 该请求需要用户登陆之后才可以访问
    @RequestMapping("/center")
    public @ResponseBody Object center() {
        return "See Center Message";
    }

    // 该请求不登录也可以访问
    @RequestMapping("/out")
    public @ResponseBody Object out() {
        return "Out See anytime";
    }

    // 如果用户未登录访问了需要登录才可访问的请求，之后会跳转至该请求路径
    @RequestMapping("/error")
    public @ResponseBody Object error() {
        return "error";
    }
}
