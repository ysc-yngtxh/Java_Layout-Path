package com.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RabbitMQConfirmApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void test2() {
		// 问题：RabbitTemplate 只允许设置一个 ConfirmCallback() 方法。
		//      但是对于实际的业务场景来说，固定一个 ConfirmCallback() 方法是不能满足需求的。
		// 方案：设置 RabbitTemplate 的作用域 @Scope("prototype")，这样就可以设置对应需求的 ConfirmCallback() 方法。
		rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {
				if (ack) {
					log.info("消息成功发送");
				} else {
					log.info("消息发送失败。错误原因：{}", cause);
				}
			}
		});
		// 第一个参数 交换机名称  第二个参数 routingKey 也就是路由key 第三个参数 消息
		// 填写正确的交换机名即可看见效果 如果想看见失败填写一个错误的交换机名称
		rabbitTemplate.convertAndSend("scopeConfirmExchange", "", "什么鬼");
	}
}
