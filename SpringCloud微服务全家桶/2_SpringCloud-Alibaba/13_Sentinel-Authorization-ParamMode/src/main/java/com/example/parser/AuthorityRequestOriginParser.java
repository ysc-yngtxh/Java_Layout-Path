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
public class AuthorityRequestOriginParser implements RequestOriginParser {
    // 假设请求来源标识是以请求参数的形式出现
    // Sentinel会自动根据parserOrigin()方法将请求解析，并且将方法返回结果设置为请求来源
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        // 获取请求参数name的值，即请求源标识
        String name = httpServletRequest.getParameter("name");
        // 若请求中没有携带name请求参数，则默认请求源为sd。
        // 且设置的授权规则白名单中是没有sd来源的，因此参数name值为空的请求为黑名单来源，会被进行限流处理。
        if (!StringUtils.hasText(name)) {
            name = "sd";
        }
        return name;
    }
}
