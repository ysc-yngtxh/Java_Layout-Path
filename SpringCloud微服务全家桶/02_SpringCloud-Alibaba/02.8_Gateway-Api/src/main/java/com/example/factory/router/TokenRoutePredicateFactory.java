package com.example.factory.router;

import java.util.List;
import java.util.function.Predicate;
import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-17 22:30
 * @apiNote TODO Token自定义路由断言规则
 */
@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenRoutePredicateFactory.Config> {

    public TokenRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            // 获取到请求中的所有header
            MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
            List<String> tokens = queryParams.get("Token");
            if (CollectionUtils.isEmpty(tokens)) {
                return false;
            }
            if (tokens.contains(config.token)) {
                return true;
            }
            return false;
        };
    }

    // 支持断言路由的重写方法
    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("Token");
    }

    @Data
    public static class Config {
        private String token;
    }
}
