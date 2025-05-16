package com.example.普通队列;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-03 12:40
 * @apiNote TODO 定义连接
 */
public class BasicConnection {

	private final static String QUEUE_NAME = "simple_queue";

	public static void main(String[] args) {
		// TODO 首先需要自定义连接，创建我们需要的 MQ队列，以供我们生产者与消费者需要
		// IP、端口，账号信息等都有默认的，可以不写，也可以自定义到想要的地址和账号中

		Connection connection = null;
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory(); // 定义连接工厂
			factory.setHost("localhost"); // 设置服务地址
			factory.setPort(5672);        // 端口

			// 设置账号信息，用户名，密码，host
			// factory.setVirtualHost("/guest");
			factory.setUsername("guest");
			factory.setPassword("guest");

			connection = factory.newConnection();    // 通过工厂获取连接
			channel = connection.createChannel();    // 从连接中创建通道，使用通道才能完成消息相关的操作
			channel.queueDeclare(QUEUE_NAME, false, false, false, null); // 声明(创建)队列
              /*
                 队列中的参数说明：
                    参数1：队列名取值任意
                    参数2：是否为持久化的队列
                    参数3：是否排外  如果排外了则这个队列只允许一个消费者监听
                    参数4：是否自动删除了，如果为true则表示当队列中没有消息，也没有消费者链接时就会自动删除这个队列
                    参数5：为队列的死信设置，通常为null即可
                 注意：
                    1、声明队列时，这个队列名称如果已经存在则放弃声明，如果队列不存在则会声明一个新的队列
                    2、队列名可以取值任意，但是要与消息接收时完全一致
                    3、这行代码是可有可无的但是一定要在发送消息前确认队列名已经存在在RabbitMQ中
               */

			/** 声明交换机,这里没用到交换机，所以不声明交换机
			 *    channel.exchangeDeclare(QUEUE_NAME, "direct", true);
			 *      交换机的参数说明:
			 *        参数1：为队列的名称取值任意
			 *        参数2：为交换机的类型 取值为 direct, fanout, topic, headers
			 *        参数3：为是否为持久化交换机*
			 *    channel.queueBind(QUEUE_NAME,QUEUE_NAME, "ysc");
			 *      绑定交换机与队列：
			 *        参数1：队列名称
			 *        参数2：交换机名称
			 *        参数3：routing-key
			 */
		} catch (IOException | TimeoutException e) {
			throw new RuntimeException(e);
		} finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException | TimeoutException e) {
					throw new RuntimeException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
