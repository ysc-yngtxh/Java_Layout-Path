package com.example;

import com.example.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RabbitSringBootApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	// 1、生产者-队列-消费者
	@Test
	public void textRabbitQueue1() {
		// 注意：由于没有使用交换机，所以不需要 binding 进行连接通信。这里的第一个参数即为队列名称
		rabbitTemplate.convertAndSend("rabbitQueue1", "第一种,基本模型");
	}

	// 2、生产者-队列-多个消费者(默认在spring AMQP实现公平调度)
	@Test
	public void textRabbitQueue2() {
		for (int i = 0; i < 10; i++) {
			// 注意：由于没有使用交换机，所以不需要 binding 进行连接通信。这里的第一个参数即为队列名称
			rabbitTemplate.convertAndSend("rabbitQueue2", "第二种,能者多劳模型");
		}
	}

	// 3、生产者-交换机(广播)-队列-消费者
	@Test
	public void textFanout() {
		rabbitTemplate.convertAndSend("rabbitExchange1", "", "第三种,广播Fanout模型");
	}

	// 4、生产者-交换机(direct)-路由-队列-消费者
	@Test
	public void textDirect() {
		rabbitTemplate.convertAndSend("rabbitExchange2", "error", "第四种,路由Direct模型(error)");
		rabbitTemplate.convertAndSend("rabbitExchange2", "normal", "第四种,路由Direct模型(normal)");
	}

	// 5、生产者-交换机(topic)-动态路由-队列-消费者
	@Test
	public void textTopic() {
		// 第一个参数 交换机名称  第二个参数 routingKey[路由key可以使用通配符进行匹配] 第三个参数 消息
		rabbitTemplate.convertAndSend("rabbitExchange3", "save.user", "第五种,动态路由Topic模型");
	}


	// 生产者(只生产不被监听消费)
	@Test
	public void publishService() {
		rabbitTemplate.convertAndSend("rabbitExchange4", "", "老黄牛，任劳任怨的生产！");
	}

	// 手动消费
	@Test
	public void ReceiveService() {
		// 消息是存在队列中，因此只需要指定队列名称即可
		Object received = rabbitTemplate.receiveAndConvert("rabbitQueue4");
		System.out.println("手动消费:" + received);
	}


	// RabbitMQ 消费各个类型的信息
	@Test
	public void textString() {
		rabbitTemplate.convertAndSend("rabbitExchange5", "rabbitKey", "游家纨绔");
	}

	@Test
	public void textInteger() {
		rabbitTemplate.convertAndSend("rabbitExchange5", "rabbitKey", 123);
	}

	@Test
	public void textUser() {
		User user = User.builder().id(123).name("游家纨绔").build();
		rabbitTemplate.convertAndSend("rabbitExchange5", "rabbitKey", user);
	}
}
