package com.example.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-23 23:00
 * @apiNote TODO
 */
@Service
public class KafkaProducerService {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	// 发送默认Topic消息（默认Topic，即在 yml文件中配置的默认Topic）
	public void sendDefault(String message) {
		// 异步发送消息
		kafkaTemplate.sendDefault(message);
	}

	// 发送普通消息
	public void sendMessage(String topic, String message) {
		// 异步发送消息
		kafkaTemplate.send(topic, message);
	}

	// 发送带 Header 的消息（用于分区路由）
	public void sendMessageWithKey(ProducerRecord<String, Object> pr) {
		kafkaTemplate.send(pr);
	}
}
