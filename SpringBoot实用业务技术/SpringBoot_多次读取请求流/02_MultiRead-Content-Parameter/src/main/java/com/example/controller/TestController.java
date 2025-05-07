package com.example.controller;

import jakarta.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 * @author 游家纨绔
 * @dateTime 2023-04-29 15:46
 * @apiNote TODO 测试应用层
 */
@Controller
public class TestController {

    @RequestMapping(value = "/test1", method = RequestMethod.POST)
    public String TestHttpRequired(@RequestParam(value = "userName") String userName,
                                   @RequestParam(value = "passWord") String passWord,
                                   Model model,
                                   ServletRequest request) {
        model.addAttribute("userName", userName);
        model.addAttribute("passWord", passWord);
        ContentCachingRequestWrapper req = (ContentCachingRequestWrapper) request;
        String user = new String(req.getContentAsByteArray());
        System.out.println("接口/test1参数"+user);
        return "test1";
    }
}
