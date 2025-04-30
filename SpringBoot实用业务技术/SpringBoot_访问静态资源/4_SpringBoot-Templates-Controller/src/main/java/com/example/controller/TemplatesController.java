package com.example.controller;

import com.example.service.StaticService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-12 16:10
 * @apiNote TODO
 */
@Controller
public class TemplatesController {

    @Autowired
    private StaticService service;

    @RequestMapping("/login")
    public String templatesResult() {
        // TODO 有一个细节需要注意：
        //  当你在配置文件中 spring.thymeleaf.prefix=classpath:/templates 时，这里return返回的视图得加斜杠"/login"。
        //  但这样配置 spring.thymeleaf.prefix=classpath:/templates/，就可以直接返回视图名称即可，否则报错。
        //  最好是在配置文件后面加上斜杠“/”，因为不加上就无法访问到默认欢迎文件index.html了
        return "login";
    }

    // 请求转发 访问静态资源
    @RequestMapping("/forward")
    public void staticResult(HttpServletRequest request, HttpServletResponse response) {
        service.staticResult(request, response);
    }

    // 重定向 访问接口路径
    @RequestMapping("/redirect")
    public void redirectResult(HttpServletResponse response) {
        service.redirectResult(response);
    }
}
