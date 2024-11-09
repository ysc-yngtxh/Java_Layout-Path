package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class GithubOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(GithubOauth2Application.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "forward:/login";
    }

    /**
     * 授权回调
     * @param code 授权编码
     */
    @RequestMapping("/github/callback")
    public @ResponseBody String AuthCallback(@RequestParam("code") String code) {
        return "Authorization code: " + code;
    }
}
