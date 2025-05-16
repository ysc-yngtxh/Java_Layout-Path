package com.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMqUtils {

	public static Channel getChannel() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost"); // 设置服务地址
		factory.setPort(5672);        // 端口

		// 设置账号信息，用户名，密码，vhost
		// factory.setVirtualHost("/guest");
		factory.setUsername("guest");
		factory.setPassword("guest");

		Connection connection = null; // 通过工程获取连接
		Channel channel = null;       // 从连接中创建通道，使用通道才能完成消息相关的操作
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
		return channel;
	}
}
