package org.example.config;

import java.util.HashMap;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 标注这是个配置类，同时注入进spring容器
public class RabbitBatchConfig {

	// 配置一个Direct类型的普通交换机
	@Bean("batchDirectExchange")
	public DirectExchange directExchange() {
		// 交换机名称   是否持久化  是否自动删除
		return new DirectExchange("batchDirectExchange", true, false);
	}

	// 配置一个普通队列  TTL 为 10S
	@Bean("batchDirectQueue")
	public Queue directQueue() {
		// TODO 第一种写法
		HashMap<String, Object> map = new HashMap<>(3);
		map.put("x-dead-letter-exchange", "deadExchange");      // 设置死信交换机
		map.put("x-dead-letter-routing-key", "deadRoutingKey"); // 设置死信routing-key
		map.put("x-message-ttl", 10000);    // 设置消息过期时间
		// map.put("x-queue-mode", "lazy"); // 设置为惰性队列
		// map.put("x-max-length", 10);     // 设置消息队列的长度
		// 参数1、队列名称  2、是否持久化  3、是否排外  4、如果队列空了是否自动删除  5、死信设置
		return new Queue("batchDirectQueue", true, false, false, map);
	}

	// 配置一个普通队列和普通交换机的绑定
	@Bean
	public Binding directBinding(@Qualifier("batchDirectExchange") DirectExchange directExchange,
	                              @Qualifier("batchDirectQueue") Queue directQueue) {
		return BindingBuilder.bind(directQueue).to(directExchange).with("bootDirectRoutingKey");
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("bootDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
	}



	// 配置一个Direct类型的死信交换机
	@Bean("deadExchange")
	public DirectExchange deadExchange() {
		return new DirectExchange("deadExchange", true, false);
	}

	// 配置一个死信队列
	@Bean("deadQueue")
	public Queue deadQueue() {
		return new Queue("deadQueue");
	}

	// 配置一个死信队列和死信交换机的绑定
	@Bean
	public Binding deadBinding(@Qualifier("deadExchange") DirectExchange directExchange,
	                           @Qualifier("deadQueue") Queue directQueue) {
		return BindingBuilder.bind(directQueue).to(directExchange).with("deadRoutingKey");
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("deadRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
	}
}
