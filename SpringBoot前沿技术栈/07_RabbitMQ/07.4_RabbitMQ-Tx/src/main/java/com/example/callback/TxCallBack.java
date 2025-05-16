package com.example.callback;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-10 22:50
 * @apiNote TODO
 */
@Slf4j
@Component
public class TxCallBack implements RabbitTemplate.ReturnsCallback {

	@Autowired
	private RabbitTemplate transactionRabbitTemplate;

	@PostConstruct
	public void init() {
		// 区别于 Confirm 模式，事务模式需要将信道设置为事务。
		// 注意：事务机制和 Confirm 机制两者是互斥的，不能同时使用。
		transactionRabbitTemplate.setChannelTransacted(true);
		transactionRabbitTemplate.setReturnsCallback(this);
	}


	@Override
	public void returnedMessage(ReturnedMessage returnedMessage) {
		log.error("在回调returnedMessage()方法中，消息:{} 被交换机{}退回，回应码：{}，退回原因：{}，路由{}"
				, new String(returnedMessage.getMessage().getBody())
				, returnedMessage.getExchange()
				, returnedMessage.getReplyCode()
				, returnedMessage.getReplyText()
				, returnedMessage.getRoutingKey()
		         );
	}
}
