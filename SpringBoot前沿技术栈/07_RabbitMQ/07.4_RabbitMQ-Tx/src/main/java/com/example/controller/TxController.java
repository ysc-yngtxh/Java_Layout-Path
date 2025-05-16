package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TxController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	// 事务机制的使用姿势，看起来和消息确认(confirm模式)差不多，无非是需要添加一个@Transactional注解罢了
	@Transactional(rollbackFor = Exception.class)
	@GetMapping("/tx/{message}")
	public void sendMsg(@PathVariable String message) {
		CorrelationData correlationData1 = new CorrelationData("1");
		rabbitTemplate.convertAndSend("confirmDirectExchange", "confirmDirectRoutingKey", message, correlationData1);
		log.info("发送消息内容：{}，设置正常的绑定交换机与路由「confirmRoutingKey」", message);

		// 当我们的路由出现问题的时候，正常来说：消息路由不到队列无法被处理就会调用 returnedMessage()方法被退回。
		CorrelationData correlationData2 = new CorrelationData("2");
		rabbitTemplate.convertAndSend("confirmDirectExchange", "confirmDirectRoutingKey1", message, correlationData2);
		log.info("发送消息内容：{}，设置不正常的绑定交换机与路由「confirmRoutingKey1」", message);
	}
}
