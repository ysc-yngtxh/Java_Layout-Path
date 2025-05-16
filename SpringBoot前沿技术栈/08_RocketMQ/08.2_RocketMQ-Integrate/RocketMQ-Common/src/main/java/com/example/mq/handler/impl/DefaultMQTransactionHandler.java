package com.example.mq.handler.impl;

import com.example.mq.handler.MQTransactionHandler;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 事务消息Service类
 */
@Service
public class DefaultMQTransactionHandler implements MQTransactionHandler {

	@Override
	public LocalTransactionState executeLocalTransaction(Message message, Object obj) {
		return null;
	}

	@Override
	public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
		return null;
	}
}
