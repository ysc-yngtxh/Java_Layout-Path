package com.example.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-22 10:00
 * @apiNote TODO 实现SpringBoot简易的RocketMQ监听
 */
@Component
@RocketMQMessageListener(
		topic = "bootTestTopic",                     // 主题
		consumerGroup = "springboot-listener-group", // 消费者组
		// 消息模式【集群模式：CLUSTERING（默认模式），广播模式：BROADCASTING】
		messageModel = MessageModel.CLUSTERING,
		// 消费者模式【CONCURRENTLY：并发消费（默认模式），ORDERLY：顺序消费】
		consumeMode = ConsumeMode.CONCURRENTLY,
		consumeThreadMax = 10,                       // 最大消费线程数，默认线程数为 64
		consumeThreadNumber = 5,                     // 消费线程数，默认线程数为 20
		maxReconsumeTimes = 3,                       // 最大重试次数，默认为-1（不进行重试）
		consumeTimeout = 3000,                       // 消费超时时间，默认时间为 15L
		replyTimeout = 3000                          // 回复超时时间，默认时间为 3000
)
public class MiniMsgListener implements RocketMQListener<MessageExt> {

	// 这个重写方法就是消费者消费消息的方法。没有返回值(消费状态)。
	// 这个方法正常执行，表示消费成功；如果出现异常，消息拒收，并且进行重试
	@Override
	public void onMessage(MessageExt msg) {
		System.out.println(new String(msg.getBody(), StandardCharsets.UTF_8));
	}
}
