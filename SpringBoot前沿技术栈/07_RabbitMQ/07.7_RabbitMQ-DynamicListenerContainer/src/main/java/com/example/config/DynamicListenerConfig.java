package com.example.config;

import com.example.utils.RabbitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitTemplate 可以不进行手动配置，SpringBoot会自动配置。直接通过 @Autowired 使用即可。
 * RabbitAdmin 需要进行手动配置，通过 @Bean 注入到IOC容器中，才能使用。
 */
@Slf4j
@Configuration
public class DynamicListenerConfig {

	/**
	 * 初始化连接工厂
	 *
	 * @param addresses RabbitMQ服务地址
	 * @param userName  用户名
	 * @param password  密码
	 * @param vhost     虚拟主机
	 */
	@Bean
	public ConnectionFactory connectionFactory(@Value("${spring.rabbitmq.addresses}") String addresses,
	                                           @Value("${spring.rabbitmq.username}") String userName,
	                                           @Value("${spring.rabbitmq.password}") String password,
	                                           @Value("${spring.rabbitmq.virtual-host}") String vhost) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setAddresses(addresses);
		connectionFactory.setUsername(userName);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(vhost);
		return connectionFactory;
	}

	/**
	 * 实例化 RabbitTemplate 操作类
	 *
	 * @param connectionFactory 连接工厂
	 */
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		// 数据转换为json存入消息队列
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		return rabbitTemplate;
	}


	/**
	 * 写法一：实例化 RabbitAdmin 操作类
	 *
	 * @param connectionFactory 连接工厂
	 */
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}
	/**
	 * 写法二：实例化 RabbitAdmin 操作类
	 * @param connectionFactory 连接工厂
	 */
	// @Bean
	// public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate) {
	//     return new RabbitAdmin(rabbitTemplate);
	// }


	/**
	 * 将 RabbitUtil 操作工具类加入IOC容器
	 *
	 * @return RabbitUtil
	 */
	@Bean
	public RabbitUtil rabbitUtil() {
		return new RabbitUtil();
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
