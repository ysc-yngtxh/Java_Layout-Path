package com.example.死信队列;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.example.RabbitMqUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 正常队列消费者C1
 */
public class Consumer1 {

	// 普通交换器名称
	public static final String NORMAL_EXCHANGE = "normal_exchange";
	// 普通队列名称
	public static final String NORMAL_QUEUE = "normal_queue";

	// 死信交换器名称
	public static final String DEAD_EXCHANGE = "dead_exchange";
	// 死信队列名称
	public static final String DEAD_QUEUE = "dead_queue";

	public static void main(String[] args) {
		try {
			// 通过封装类获取Channel信道
			Channel channel = RabbitMqUtils.getChannel();

			// 声明普通、死信交换器
			channel.exchangeDeclare(NORMAL_EXCHANGE, "direct", false);
			channel.exchangeDeclare(DEAD_EXCHANGE, "direct", false);
			// 声明普通队列
			Map<String, Object> map = new HashMap<>(3); // map集合长度赋值3，提高性能
			map.put("x-dead-letter-exchange", DEAD_EXCHANGE); // 设置死信交换器(x-dead-letter-exchange是固定写法)
			map.put("x-dead-letter-routing-key", "lisi"); // 设置死信routing-key(x-dead-letter-routing-key是固定写法)
			// 设置正常队列的长度限制.正常队列只能积压6个，超过6条的会成为死信(x-max-length是固定写法)。队列的默认长度是无限的
			map.put("x-max-length", 6);
			// map.put("x-message-ttl", 10000); // 过10秒没被消费就一旦超会成为死信
			channel.queueDeclare(NORMAL_QUEUE, false, false, false, map);
			// 声明死信队列
			channel.queueDeclare(DEAD_QUEUE, false, false, false, null);

			// 绑定普通交换器与队列
			channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "ZhangSan");
			// 绑定死信交换器与死信队列
			channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lisi");

			System.out.println("等待接收消息......");

			// 监听到相应队列后的回调方法
			DeliverCallback callback = (consumerTag, message) -> {
				String msg = new String(message.getBody(), StandardCharsets.UTF_8);
				if ("info--5".equals(msg)) {
					System.out.println("此消息是被C1拒绝的" + msg);
					// 这里做消息拒绝   message.getEnvelope().getDeliveryTag(): 消息id。
					// requeue表示消息是否重回队列: 如果requeue=false表示消息不重回队列并且丢到死信，如果为requeue=true则消息重回队列
					channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
				} else {
					System.out.println("Consumer1接受的消息是：" + msg);
					// 这里做应答处理  message.getEnvelope().getDeliveryTag(): 消息id。
					// multiple表示是否一次消费多条消息，false表示只确认该序列号对应的消息，true则表示确认该序列号对应的消息以及比该序列号小的所有消息
					channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
				}
			};

			channel.basicConsume(NORMAL_QUEUE, false, callback, consumer -> {
			});

            /* 队列中的参数说明：
             参数1：为当前消费者需要监听的队列名，队列名必须要与发送时的队列名完全一致，否则接收不到消息
             参数2：为是否自动进行消息确认.true表示自动进行ACK，false表示手动ACK
             参数3：消息传递回调（DeliverCallback）：用于定义当消息到达时应执行的操作。
                   当你从RabbitMQ收到一条消息时，会触发这个回调。
                   它包含一个方法 handle，该方法接受两个参数：consumerTag（消费者的标签）和 delivery（消息封装对象，包含消息体、属性、路由键等信息）。
                   通过这个回调，你可以实现对消息的具体处理逻辑。
             参数4：取消消费回调（CancelCallback）：用于定义当消费被意外取消时应执行的操作。
                   例如，如果RabbitMQ服务器主动关闭了连接或通道，导致无法继续消费消息，则会触发这个回调。
                   它允许你执行一些清理工作或者记录日志等操作。
             */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
