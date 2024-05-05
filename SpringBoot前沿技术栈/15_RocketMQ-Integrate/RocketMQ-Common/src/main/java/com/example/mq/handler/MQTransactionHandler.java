package com.example.mq.handler;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 事务消息处理类
 */
public interface MQTransactionHandler {

    LocalTransactionState executeLocalTransaction(Message message, Object o);

    LocalTransactionState checkLocalTransaction(MessageExt messageExt);
}
