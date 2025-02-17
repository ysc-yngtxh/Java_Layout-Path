package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicListenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicListenerApplication.class, args);
	}

	/**
	 * SpringBoot 集成 RabbitMQ
	 * 正常情况下，需要事先配置 交换机、队列、绑定关系，以及监听消息的消费逻辑。如果我们的业务场景是固定的，那么这种方式是没有问题的。
	 * 但是，如果我们的业务场景是需要动态创建队列、动态监听队列，那么我们就需要动态监听队列.
	 *
	 * 那么就需要 RabbitAdmin 进行动态配置 交换机、队列、绑定关系。
	 * SimpleMessageListenerContainer 动态监听队列。
	 */
}
