package com.example.config;

import java.util.HashMap;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-18 22:35
 * @apiNote TODO Headers 类型交换机配置
 */
@Configuration
public class HeadersExchangeConfig {

	// 配置一个 Headers 类型的交换机
	@Bean
	public HeadersExchange headerExchange() {
		return new HeadersExchange("headerExchange");
	}

	// 配置队列: headerQueue1
	@Bean
	public Queue headerQueue1() {
		return new Queue("headerQueue1");
	}

	// 配置队列: headerQueue2
	@Bean
	public Queue headerQueue2() {
		return new Queue("headerQueue2");
	}

	// 配置绑定关系
	@Bean
	public Binding headerBinding1(@Qualifier("headerQueue1") Queue headerQueue1,
	                              @Qualifier("headerExchange") HeadersExchange headerExchange) {
		HashMap<String, Object> header = new HashMap<>();
		header.put("queue", "headerQueue1");
		header.put("bindType", "whereAll");
		// whereAll: 匹配所有条件
		return BindingBuilder.bind(headerQueue1).to(headerExchange).whereAll(header).match();
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           whereAll(header)：设置绑定参数，表示指定的头部（Headers）必须匹配。【必须匹配所有条件】
           match()：完成绑定的构建，表示需要满足所有头部匹配条件。
        */
	}

	// 配置绑定关系
	@Bean
	public Binding headerBinding2(@Qualifier("headerQueue2") Queue headerQueue2,
	                              @Qualifier("headerExchange") HeadersExchange headerExchange) {
		HashMap<String, Object> header = new HashMap<>();
		header.put("queue", "headerQueue2");
		header.put("bindType", "whereAny");
		// whereAny: 匹配任意条件
		return BindingBuilder.bind(headerQueue2).to(headerExchange).whereAny(header).match();
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           whereAny(header)：设置绑定参数，表示指定的头部（Headers）必须匹配。【匹配任意条件即可】
           match()：完成绑定的构建，表示需要满足任意头部匹配条件。
        */
	}
}
