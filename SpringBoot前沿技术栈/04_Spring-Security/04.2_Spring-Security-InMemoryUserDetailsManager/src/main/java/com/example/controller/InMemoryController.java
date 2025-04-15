package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-17 08:07
 * @apiNote TODO 控制层
 */
@Controller
public class InMemoryController {

    // TODO 根据 RESTful 设计原则，GET请求应该用于获取资源，而不应该用于更改服务器上的数据。
    //      因此默认情况下，Spring Security和其他类似的框架不会对GET请求应用CSRF保护，因为GET请求不应该产生副作用。
    //      相应的，Spring Security 默认对 GET、HEAD、TRACE、OPTIONS 请求不进行 CSRF 保护
    @RequestMapping("/admin")
    public String UserAdmin() {
        return "admin";
    }

    @PostMapping(value = "/manager")
    public String UserManager() {
        return "manager";
    }

    @PostMapping(value = "/button")
    public String UserButton() {
        return "button";
    }

    @PostMapping(value = "/xor")
    public @ResponseBody String UserXor() {
        return "xor";
    }

    @PostMapping(value = "/custom")
    public @ResponseBody String UserCustom() {
        return "custom";
    }
}
