package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.pojo.Order;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
public class RetryController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Resource
	private StringRedisTemplate stringRedisTemplate;

	private RabbitTemplate messageCallBack() {
		// 交换机收到消息发布确认的回调方法
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			String id = correlationData != null ? correlationData.getId() : "";
			if (ack) {
				log.info("交换机在回调confirm()方法中收到correlationData为：{}的消息", id);
			} else {
				log.info("交换机在回调confirm()方法中还未收到correlationData为：{}的消息，由于原因:{}", id, cause);
			}
		});

		// 队列收到消息路由失败的回退处理，如果失败的话会进行 returnCallback 的回调处理，反之成功就不会回调。
		rabbitTemplate.setReturnsCallback(returned -> {
			// 请注意!如果你使用了延迟队列插件，那么一定会调用该callback方法，因为数据并没有发布出去，而是提交在交换器中，过期时间到了才发布出去，
			// 并非是bug！你可以用if进行判断交换机名称来捕捉该报错
			if ("integrationExchange".equals(returned.getExchange()) || "orderExchange".equals(returned.getExchange())) {
				return;
			}
			log.info("returnCallback: 消息：{}", returned.getMessage());
			log.info("returnCallback: 回应码：{}", returned.getReplyCode());
			log.info("returnCallback: 回应信息：{}", returned.getReplyText());
			log.info("returnCallback: 交换机：{}", returned.getExchange());
			log.info("returnCallback: 路由键：{}", returned.getRoutingKey());
		});
		return rabbitTemplate;
	}

	@GetMapping("/sendOrder")
	public ResponseEntity<String> sendDelay(@RequestBody Order order) {
		log.info("【订单生成时间】:{}——【1分钟后检查订单是否已经支付】:{}"
				, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
				, order.toString());
		// 实现局部的回调方法，这样就不用将固定的回调方法逻辑写死，方便扩展
		RabbitTemplate rabbit = messageCallBack();

		CorrelationData data = new CorrelationData(order.getOrderId());
		rabbit.convertAndSend("orderExchange"
				, "orderRoutingKey"
				, JSON.toJSONString(order)  // 这里对象要转为json格式String类型，方便在消费时redis序列化
				, msg -> {
					// msg.getMessageProperties().setExpiration("10*1000"); // 过期时间
					msg.getMessageProperties().setDelayLong(10 * 1000L);
					return msg;
				}
				, data);
		return ResponseEntity.status(HttpStatus.OK).body("成功访问");
	}

	// redis重试机制
	@PostMapping("/retry")
	public ResponseEntity<String> retry(@RequestBody Order order) {
		String orderId = order.getOrderId();
		// String message = (String)stringRedisTemplate.opsForHash().get("test", orderId);
		String message = JSON.toJSONString(order);
		CorrelationData data = new CorrelationData(orderId);
		// 设置局部的消息确认机制
		rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
			if (ack) {
				// 消息成功投递到交换机
				stringRedisTemplate.opsForHash().delete("test", orderId);
			} else {
				// 消息投递交换机失败，进行重试
				stringRedisTemplate.opsForHash().put("test", orderId, message);
			}
		});
		// 设置局部的消息返回机制
		rabbitTemplate.setReturnsCallback(returned -> log.error("消息路由队列失败: {}", returned.getReplyText()));

		rabbitTemplate.convertAndSend("integrationExchange"
				, "integrationRoutingKey"
				, message
				, msg -> {
					// msg.getMessageProperties().setExpiration("10*1000"); // 过期时间
					msg.getMessageProperties().setDelayLong(10 * 1000L); // 延迟时间，这里可以不做延迟时间的，但还是先写出来，怕以后忘了
					return msg;
				}
				, data);
		return ResponseEntity.status(HttpStatus.OK).body("成功访问");
	}

}
