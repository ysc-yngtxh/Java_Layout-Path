package com.example.mq.handler;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 消息处理类
 */
public interface MQHandler {

    ConsumeConcurrentlyStatus handle(String tag, MessageExt messageExt);
}
