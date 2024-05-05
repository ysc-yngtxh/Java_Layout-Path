package com.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-18 15:02
 * @apiNote TODO 实现Spring Cloud Gateway的路由以及过滤规则
 */
@RestController
public class GatewayController {

    @GetMapping("/header")
    public String HeaderHandler(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder sb = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String nextName = headerNames.nextElement();
            Enumeration<String> headers = request.getHeaders(nextName);
            while (headers.hasMoreElements()) {
                String nextElement = headers.nextElement();
                if (nextName.equalsIgnoreCase("X-Request-Color") || nextName.equalsIgnoreCase("City")) {
                    sb.append("<span style=\"color:blue\">").append(nextName).append(": ")
                            .append(nextElement).append("</span><br/>");
                } else {
                    sb.append(nextName).append(": ").append(nextElement).append("<br/>");
                }
            }
        }
        // response返回Header无法在代码中获取，只能在浏览器控制台中观察
        sb.append("<span style=\"color:green\">").append("X-Response-Red").append(": ")
                .append("请看控制台响应参数(X-Response-Red)是否存在！").append("</span><br/>");
        return sb.toString();
    }

    @GetMapping("/param1")
    public String ParamHandler1(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        for (String key : parameterMap.keySet()) {
            sb.append(key).append(": ");
            for (String value : parameterMap.get(key)) {
                sb.append(value).append(" ");
            }
            sb.append("</br>");
        }
        return sb.toString();
    }

    @GetMapping("/param2")
    public String ParamHandler2(String color, String[] city) {
        StringBuilder sb = new StringBuilder();
        sb.append("color").append(": ").append(color);
        sb.append("</br>").append("city").append(":");
        for (String CityValue : city) {
            sb.append(" ").append(CityValue);
        }
        sb.append("</br>");
        return sb.toString();
    }
}
