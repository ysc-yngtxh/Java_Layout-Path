package com.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BackupsConsumer {

	@RabbitListener(queues = "backupsQueue")
	public void sendMessage(Message message) {
		String msg = new String(message.getBody());
		log.info("正常队列接收到的消息：{}", msg);
	}


	// 这里既然设置了备份交换机模式，那么程序就不走回退消息给生产者。所以回退的日志没打印
	@RabbitListener(queues = "warningQueue")
	public void warning(Message message) {
		String msg = new String(message.getBody());
		log.info("备份队列报警发现不可用路由,其无法路由到的消息：{}", msg);
	}
}
