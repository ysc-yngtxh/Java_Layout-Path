package com.example.init.exception;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-18 14:00
 * @apiNote TODO 自定义异常接口
 */
@Component
public class CustomExceptionMode implements CommandLineRunner {

    // 自定义异常接口（也可以通过配置文件的方式 scg 实现。如两种方式都存在，该API方式优先级高）
    @Override
    public void run(String... args) throws Exception {
        // 第一种方式：直接重定向响应异常接口
        // GatewayCallbackManager.setBlockHandler(new RedirectBlockRequestHandler("https://baidu.com"));

        // 第二种方式：通过返回响应流结束异常接口
        GatewayCallbackManager.setBlockHandler((exchange, th) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("uri", exchange.getRequest().getURI());
            map.put("msg", "访问量过大，稍后请重试");
            map.put("status", 401);
            return ServerResponse.status(HttpStatus.TOO_EARLY)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(map));
        });
    }
}
