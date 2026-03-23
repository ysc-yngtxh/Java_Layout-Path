package com.example;

import com.example.pojo.User;
import com.example.service.KafkaProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
public class SpringBootKafkaApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaApplication.class, args);
	}

	@Autowired
	private KafkaProducerService kafkaProducerService;

	// TODO https://www.yanfukun.com/read/springaction/Kafka 参考文章
	@Override
	public void run(ApplicationArguments args) {
		// 发送普通 Topic 消息
		kafkaProducerService.sendMessage("my-topic", "Hello, Kafka!");

		// 发送带有 Headers 的消息
		User user = new User(1, "游家纨绔", "18");
		// ProducerRecord封装了消息的所有元数据和内容，可简单理解为消息的包装体
		ProducerRecord<String, User> pr = new ProducerRecord<>("headers-topic", user);
		pr.headers().add("user", user.getClass().getName().getBytes(StandardCharsets.UTF_8));
		kafkaProducerService.sendMessageWithKey(pr);

        // 发送事务的消息
        kafkaProducerService.sendTransactionalMessage("my-topic", user, user);

        // 发送批量的消息
        List<User> users = List.of(new User(1, "游家纨绔1", "18"), new User(2, "游家纨绔2", "19"), new User(3, "游家纨绔3", "20"));
        kafkaProducerService.sendBatchMessages("batch-topic", users);

        // 发送具有过滤功能的消息
        kafkaProducerService.sendFilterMessage("filtered-topic", users);
    }

}
