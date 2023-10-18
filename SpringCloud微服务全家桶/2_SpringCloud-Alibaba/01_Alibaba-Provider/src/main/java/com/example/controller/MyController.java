package com.example.controller;

import com.example.pojo.User;
import com.example.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.Map;

/**
 * @author 游家纨绔
 */
@RestController
@RequestMapping("/provider")
public class MyController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public User query(@PathVariable("id") Integer id){
        return userService.queryById(id);
    }

    @GetMapping("/header")
    public String HeaderHandler(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder builder = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String nextName = headerNames.nextElement();
            Enumeration<String> headers = request.getHeaders(nextName);
            while (headers.hasMoreElements()) {
                String nextElement = headers.nextElement();
                if (nextName.equalsIgnoreCase("X-Request-Color") || nextName.equalsIgnoreCase("City")) {
                    builder.append("<span style=\"color:blue\">").append(nextName).append(": ")
                            .append(nextElement).append("</span><br/>");
                } else {
                    builder.append(nextName).append(": ").append(nextElement).append("<br/>");
                }
            }
        }
        return builder.toString();
    }

    @GetMapping("/param")
    public String ParamHandler(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder builder = new StringBuilder();
        for (String key : parameterMap.keySet()) {

        }
        return builder.toString();
    }
}
