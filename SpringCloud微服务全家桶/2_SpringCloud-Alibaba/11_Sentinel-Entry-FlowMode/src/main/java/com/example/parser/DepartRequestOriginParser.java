package com.example.parser;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-01 16:09
 * @apiNote TODO 定义流控中的请求来源
 */
@Component
public class DepartRequestOriginParser implements RequestOriginParser {
    // 假设请求来源标识是以请求参数的形式出现
    // Sentinel会自动根据parserOrigin()方法的返回结果来判断请求源
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 获取请求参数source的值，即请求源标识
        String name = httpServletRequest.getParameter("name");
        // 若请求中没有携带name请求参数，则默认请求源为ysc
        if (!StringUtils.hasText(name)) {
            name = "ysc";
        }
        return name;
    }
}
