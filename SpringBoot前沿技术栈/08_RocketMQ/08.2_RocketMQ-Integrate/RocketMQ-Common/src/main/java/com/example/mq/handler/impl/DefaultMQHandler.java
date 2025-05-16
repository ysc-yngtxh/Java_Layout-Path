package com.example.mq.handler.impl;

import com.example.mq.handler.MQHandler;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 消息Service类
 */
@Service
public class DefaultMQHandler implements MQHandler {

	@Override
	public ConsumeConcurrentlyStatus handle(String tag, MessageExt messageExt) {
		return null;
	}
}
