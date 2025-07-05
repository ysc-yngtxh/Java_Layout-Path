package com.example.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * 队列监听类（静态）
 */
@Slf4j
@Configuration
public class StaticListener {

	/**
	 * 监听指定队列，名称：rabbitQueue2
	 *
	 * @param message 消息内容
	 * @param channel 信道
	 *                ⚠️：静态队列监听器中注册的队列名，必须在RabbitMQ客户端声明存在，否则应用启动会报错
	 */
	@RabbitListener(queues = "rabbitQueue2")
	public void consume(Message message, Channel channel) {
		log.info("StaticListener，收到消息: {}", message.toString());
	}

}
