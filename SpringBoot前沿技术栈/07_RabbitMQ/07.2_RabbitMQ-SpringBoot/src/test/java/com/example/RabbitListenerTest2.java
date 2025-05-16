package com.example;

import com.example.entity.User;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-04 08:20
 * @apiNote TODO
 */
@Component
@RabbitListener(bindings = {
		@QueueBinding(
				exchange = @Exchange(value = "rabbitExchange5"),
				key = {"rabbitKey"},
				value = @Queue(value = "rabbitQueue5")
		)
})
public class RabbitListenerTest2 {

	// @RabbitHandler 消息处理器，它可以根据消息类型来选择不同的消息处理方法
	// @Payload注解指定要接收的消息体类型。可加可不加
	@RabbitHandler
	public void handleMessage(@Payload String message) {
		System.out.println("Received string message: " + message);
	}

	@RabbitHandler
	public void handleMessage(@Payload Integer message) {
		System.out.println("Received integer message: " + message);
	}

	// 如果消息体类型不是简单类型，我们还需要在类上添加@RabbitListener注解指定要监听的队列。
	@RabbitHandler
	public void handleMessage(@Payload User user) {
		System.out.println("Received User message: " + user);
	}
}
