package com.example.service;

import com.example.pojo.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-23 23:00
 * @apiNote TODO
 */
@Slf4j
@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaTemplate<String, User> userKafkaTemplate;

    @Resource
    private KafkaTemplate<String, String> kafkaTransactionalTemplate;



    // 发送默认Topic消息（默认Topic，即在 yml文件中配置的默认Topic）
	public void sendDefault(String message) {
		// 异步发送消息
		kafkaTemplate.sendDefault(message);
	}

	// 发送普通消息
	public void sendMessage(String topic, String message) {
        log.info("发送消息到 {}: {}", topic, message);

        // 异步发送，获取 CompletableFuture
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("消息发送成功! Topic: {}, Partition: {}, Offset: {}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("消息发送失败: {}", ex.getMessage(), ex);
            }
        });
	}

	// 发送带 Header 的消息（用于分区路由）
	public void sendMessageWithKey(ProducerRecord<String, User> pr) {
        userKafkaTemplate.send(pr);
	}

    // 发送 Kafka 事务性：保证数据库操作和消息发送的原子性
    @Transactional
    public void sendTransactionalMessage(String topic, User message1, User message2) {
        // 1. 数据库操作...
        // 2. 发送消息
        kafkaTransactionalTemplate.executeInTransaction(operations -> {
            operations.send(topic, message1.getId().toString(), message1.toString());
            // 模拟业务处理，可能会抛出异常
            // if (Math.random() < 0.5) {
            //     throw new RuntimeException("Simulated business exception");
            // }
            operations.send(topic, message2.getId().toString(), message2.toString());
            return null;
        });
    }

    // 发送批量消息
    public void sendBatchMessages(String topic, List<User> messages) {
        messages.forEach(message -> kafkaTemplate.send(topic, message.toString()));
        // 刷新缓冲区，确保消息发出。（同步模式，性能较低）
        kafkaTemplate.flush();
    }

    // 发送具有过滤功能的消息
    public void sendFilterMessage(String topic, List<User> users) {
        users.forEach(message -> userKafkaTemplate.send(topic, "filter", message));
        // 刷新缓冲区，确保消息发出。（同步模式，性能较低）
        kafkaTemplate.flush();
    }

}
