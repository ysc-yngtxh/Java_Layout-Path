package com.example.listener;

import com.example.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-23 23:00
 * @apiNote TODO
 */
@Slf4j
@Configuration
public class KafkaMessageListener {

    // topics：指定要监听的主题，groupId：指定消费者组ID
	@KafkaListener(topics = "my-topic", groupId = "my-group")
	public void consume(@Payload String message, Acknowledgment ack) {
        log.info("收到消息 [test-topic]: {}", message);

        // 模拟业务处理
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        log.info("消息处理完成: {}", message);

        // 如果配置了 ack-mode: MANUAL，这里需要手动提交偏移量.
        ack.acknowledge();  // 手动确认消息已消费，避免重复消费
	}

	// 监听带 Header 的消息
	@KafkaListener(topics = "headers-topic")
	public void consumeWithHeaders(@Payload String message,
	                               @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
	                               @Header(value = "user", required = false) String user) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		User obj = objectMapper.readValue(message, User.class);
		System.out.println("Message: " + obj + " from partition: " + partition + " user: " + user);
	}

    // 监听消息，接收批量消息
    @KafkaListener(topics = "batch-topic", containerFactory = "batchFactory")
    public void consumeBatchMessages(@Payload List<User> messages) {
        log.info("Received batch of {} messages", messages);
    }

    // 监听消息，结合自定义过滤器过滤消息
    @KafkaListener(topics = "filtered-topic", groupId = "json-group", containerFactory = "batchFactory", filter = "kafkaMessageFilter")
    public void consumeFilteredMessage(@Payload List<User> messages) {
        log.info("Received filtered message: {}", messages);
    }

}
