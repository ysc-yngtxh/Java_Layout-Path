package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-23 23:08
 * @apiNote TODO
 */
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        // 异步发送消息
        kafkaTemplate.send(topic, message);
    }
}
