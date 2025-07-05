package org.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/batch")
public class RabbitBatchController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	// 开始发消息
	@GetMapping("/sendMsg/{message}/{number}")
	public void sendMsg(@PathVariable String message, @PathVariable Integer number) {
		log.info("当前时间：{},发送消息给批量处理队列：{}"
				, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
				, message);
		for (int i = 1; i <= number; i++) {
			rabbitTemplate.convertAndSend("batchDirectExchange", "bootDirectRoutingKey", "消息来自batch为10s的队列：" + message + "-" + i);
		}
	}

}
