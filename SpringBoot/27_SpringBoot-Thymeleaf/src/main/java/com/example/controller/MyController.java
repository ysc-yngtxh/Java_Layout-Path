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

    @RequestMapping("/user")
    public String userDetail() {
        // TODO 有一个细节需要注意：
        //  当你在配置文件中 spring.thymeleaf.prefix=classpath:/templates 时，这里return返回的视图得加斜杠"/message"。
        //  但这样换一种配置 spring.thymeleaf.prefix=classpath:/templates/时，就可以直接返回视图名称"message"即可，否则报错。
        //  最好是在配置文件后面加上斜杠“/”，因为不加上就无法访问到默认欢迎文件index.html了
        return "user";
    }

    @RequestMapping("/user/detail")
    public String userDetail1(Model model) {
        User user = new User();
        user.setId(11);
        user.setUsername("曹家千金");
        user.setAge(21);
        model.addAttribute("user", user);
        return "message";
    }

    @RequestMapping("/url")
    public String userDetail2(Model model, String username) {
        model.addAttribute("id", 11);
        return "url";
    }

    @RequestMapping("/test")
    public @ResponseBody String userDetail3(Model model, String username) {
        return "请求路径/test,参数username:" + username;
    }

    @RequestMapping("/text/{id}")
    public @ResponseBody String userDetail3(@PathVariable("id") Integer id) {
        return "ID=" + id;
    }
}
