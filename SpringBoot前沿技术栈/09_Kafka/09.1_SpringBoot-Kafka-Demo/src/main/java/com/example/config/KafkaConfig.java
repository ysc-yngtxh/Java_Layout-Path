package com.example.config;

import com.example.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

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

    // 监听带 Header 的消息
    @KafkaListener(topics = "headers-topic")
    public void consumeWithHeaders(@Payload String message,
                                   @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                   @Header(value = "user", required = false) String user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User obj = objectMapper.readValue(message, User.class);
        System.out.println("Message: " + obj + " from partition: " + partition);
    }
}
