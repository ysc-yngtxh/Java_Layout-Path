package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class GiteeOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(GiteeOauth2Application.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "forward:/login";
    }


    @RequestMapping("/oauth2/gitee")
    public @ResponseBody String GiteeAuthCallback() {
        return "Hello World！认证成功！！！";
    }
}
