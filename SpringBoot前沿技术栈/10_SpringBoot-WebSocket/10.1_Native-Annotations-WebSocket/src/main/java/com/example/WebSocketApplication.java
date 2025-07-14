package com.example;

import com.example.websocket.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebSocketApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(WebSocketApplication.class, args);
        WebSocketServer.setApplicationContext(applicationContext);
    }

    /* 实现 WebSocket 的方式一
     *     使用原生注解 WebSocket 应用
     */

}
