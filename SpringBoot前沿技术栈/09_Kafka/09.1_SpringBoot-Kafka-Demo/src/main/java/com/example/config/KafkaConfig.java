package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-23 23:09
 * @apiNote TODO
 */
@Configuration
public class KafkaConfig {

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}
