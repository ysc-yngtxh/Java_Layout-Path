package com.example.controller;

import com.example.handler.HandlerRegistry;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/handlers")
public class HandlerController {

	// 使用构造器注入 HandlerRegistry 对象
	private final HandlerRegistry handlerRegistry;

	public HandlerController(HandlerRegistry handlerRegistry) {
		this.handlerRegistry = handlerRegistry;
	}


	@PostMapping("/order/{messageType}")
	public void registerHandler(@PathVariable String messageType) {
		handlerRegistry.addHandler(messageType, (payload, channel, deliveryTag) -> {
			// Order order = (Order) payload;
			// 处理订单逻辑...
			channel.basicAck(deliveryTag, false);
		});
	}

	@PostMapping("/payment/{messageType}")
	public void registerHandler2(@PathVariable String messageType) {
		handlerRegistry.addHandler(messageType, (payload, channel, deliveryTag) -> {
			// Payment payment = (Payment) payload;
			// 处理支付逻辑...
			channel.basicAck(deliveryTag, false);
		});
	}

	@DeleteMapping("/remove/{messageType}")
	public void unregisterHandler(@PathVariable String messageType) {
		handlerRegistry.removeHandler(messageType);
	}
}
