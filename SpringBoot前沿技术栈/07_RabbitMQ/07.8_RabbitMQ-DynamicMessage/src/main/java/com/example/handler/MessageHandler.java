package com.example.handler;

import com.rabbitmq.client.Channel;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-17 19:00
 * @apiNote TODO 消息处理器接口
 */
@FunctionalInterface
public interface MessageHandler {

	void handle(Object payload, Channel channel, long deliveryTag) throws Exception;
}
