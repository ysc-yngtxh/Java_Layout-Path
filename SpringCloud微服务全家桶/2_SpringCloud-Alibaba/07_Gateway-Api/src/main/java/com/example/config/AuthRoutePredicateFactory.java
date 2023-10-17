package com.example.config;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-17 22:27
 * @apiNote TODO 自定义路由断言规则
 */
@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {

    public AuthRoutePredicateFactory(Class<Config> configClass) {
        super(configClass);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            // 获取到请求中的所有header
            HttpHeaders headers = exchange.getRequest().getHeaders();
            // 一个请求头可以包含多个值
            List<String> pwds = headers.get(config.userName);
            // 只要请求头中指定的多个密码值中包含了配置文件中指定的密码，就可以通过
            if (pwds.contains(config.passWord)) {
                return true;
            }
            return false;
        };
    }

    @Data
    public static class Config {
        private String userName;
        private String passWord;
    }
}
