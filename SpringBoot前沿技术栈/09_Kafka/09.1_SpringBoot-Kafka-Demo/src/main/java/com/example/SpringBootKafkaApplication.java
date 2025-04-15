package com.example;

import com.example.pojo.User;
import com.example.service.KafkaProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKafkaApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaApplication.class, args);
	}

	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Override
	public void run(ApplicationArguments args) throws JsonProcessingException {
		// 发送普通 Topic 消息
		kafkaProducerService.sendMessage("my-topic", "Hello, Kafka!");

		// 发送带有 Headers 的消息
		User user = new User(1, "游家纨绔", "18");
		ObjectMapper objectMapper = new ObjectMapper();
		// ProducerRecord封装了消息的所有元数据和内容，可简单理解为消息的包装体
		ProducerRecord<String, String> pr = new ProducerRecord<>("headers-topic", objectMapper.writeValueAsString(user));
		pr.headers().add("user", user.getClass().getName().getBytes(StandardCharsets.UTF_8));
		kafkaProducerService.sendMessageWithKey(pr);
	}
}
