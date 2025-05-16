package com.example;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class RabbitListenerTest {

	// 创建 RabbitTemplate 对象，并且将 Bean 设置为原型模式
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		// 设置RabbitMQ为强制模式：但消息不可达队列时，消息是返回消息而不是直接丢弃
		template.setMandatory(true);
		template.setMessageConverter(new SerializerMessageConverter()); // 设置序列化方式
		return template;
	}


	@RabbitListener(bindings = {  // bindings是交换机连接队列的规则
			@QueueBinding(
					exchange = @Exchange(value = "scopeConfirmExchange", type = "topic"), // 交换机的名称和类型
					value = @Queue() // 这里没有给Queue的value值，表示是个暂时的队列
			)
	})
	public void confirm(String message) {
		System.out.println("message = " + message);
	}
}
