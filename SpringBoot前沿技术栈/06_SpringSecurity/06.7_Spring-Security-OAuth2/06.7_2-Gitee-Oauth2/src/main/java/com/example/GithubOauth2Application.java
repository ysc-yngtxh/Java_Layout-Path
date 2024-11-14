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


    @RequestMapping("/oauth2/github")
    public @ResponseBody String GithubAuthCallback() {
        return "Authorization code: ";
    }

    @RequestMapping("/oauth2/gitee")
    public @ResponseBody String GiteeAuthCallback() {
        return "Authorization code: ";
    }
}
