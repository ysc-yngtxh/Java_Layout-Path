package com.example.controller;

import com.example.pojo.vo.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: 用户权限访问接口
 * @date 2022/07/30 20:53
 */
@Controller
public class UserController {

    // TODO 这里的接口是在用户登录后使用的，我们可以逐个访问，根据不同用户的相应权限，允已放行

    @RequestMapping("/sayHello")
    // 权限注解，即：访问这个接口前需要当前用户有 'test123' 权限
    @PreAuthorize("hasAuthority('test123')")
    public @ResponseBody String Hello() {
        return "hello world!";
    }

    @RequestMapping("/test")
    // 权限注解，即：访问这个接口前需要当前用户有 'message' 权限
    @PreAuthorize("hasAuthority('test')")
    public @ResponseBody String test() {
        return "Hello World";
    }

    @RequestMapping("/role")
    // 权限注解，即：访问这个接口前需要当前用户有 'vip1' 角色
    @PreAuthorize("hasRole('vip1')")
    public @ResponseBody String role() {
        return "关关雎鸠，在河之洲;";
    }

    @RequestMapping("/code")
    public @ResponseBody ResponseResult<String> code() {
        return new ResponseResult<>(200, "无敌是多么，多么寂寞~", null);
    }
}
