package com.example.handler;

import com.example.mq.annotation.MQHandlerActualizer;
import com.example.mq.handler.MQHandler;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO rocketmq-example
 */
@MQHandlerActualizer(topic = "order")
public class OrderMQHandler implements MQHandler {

	@Override
	public ConsumeConcurrentlyStatus handle(String tag, MessageExt messageExt) {
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
}
