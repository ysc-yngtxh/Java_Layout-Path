package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicMessageApplication.class, args);
    }

    /**
     * 虽然我们通过 SimpleMessageListenerContainer 实现了动态监听器的注册和注销，
     * 但是对于监听消息的消费逻辑，在代码中还是属于硬编码，这样会导致代码的耦合性增加。
     *
     * 因此，该 模块 主要功能就是为了能够动态注册和注销消息处理器，从而实现消息消费逻辑的解耦。
     */
}
