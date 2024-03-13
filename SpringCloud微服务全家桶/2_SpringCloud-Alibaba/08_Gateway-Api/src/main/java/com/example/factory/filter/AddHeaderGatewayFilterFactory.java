package com.example.factory.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-05 12:20
 * @apiNote TODO AddHeader自定义过滤规则
 */
@Component
public class AddHeaderGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    // 在传入的配置项（NameValueConfig）中指定的请求头中添加一个新的请求头，并将修改后的请求转发给下一个过滤器链（Filter Chain）。
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            // 使用ServerWebExchange工具类获取到uri变量的Map集合
            Map<String, String> uriVariables = ServerWebExchangeUtils.getUriTemplateVariables(exchange);
            // 需要注意的是，变量名称要与 yml文件中的变量名称 对应好，否则获取不到对应数据
            System.out.println("segment值为：" + uriVariables.get("segment"));
            System.out.println("bean值为：" + uriVariables.get("bean"));

            // 使用mutate()方法复制原始请求（exchange.getRequest()），
            // 然后使用header()方法，将指定的名称（config.getName()）和值（config.getValue()）添加为请求的一个新头部。
            ServerHttpRequest changedRequest = exchange.getRequest()
                    .mutate()
                    .header(config.getName(), config.getValue())
                    .build();

            // 使用复制的原始Exchange对象（exchange.mutate()），将修改后的请求（changedRequest）设置为新的请求，并构建一个新的ServerWebExchange对象。
            ServerWebExchange changedExchange = exchange.mutate()
                    .request(changedRequest)
                    .build();

            // 返回filter chain的下一个过滤器链，并传入修改后的Exchange对象。这将使修改的请求进一步通过后续的过滤器。
            return chain.filter(changedExchange);
        };
    }
}