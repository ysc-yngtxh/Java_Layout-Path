package com.example.controller;

import com.example.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 */
@Controller
public class MyController {

    @RequestMapping("/user/detail")
    public String userDetail1(Model model){
        User user = new User();
        user.setId(1001);
        user.setUsername("李晶晶");
        user.setAge(21);
        model.addAttribute("user",user);
        return "message";
    }

    @RequestMapping("/url")
    public String userDetail2(Model model, String username){
        model.addAttribute("id",1001);
        return "url";
    }

    @RequestMapping("/test")
    public @ResponseBody String userDetail3(Model model,String username){

        return "请求路径/test,参数username:"+username;
    }

    @RequestMapping("/text/{id}")
    public @ResponseBody String userDetail3(@PathVariable("id") Integer id){

        return "ID="+id;
    }
}
