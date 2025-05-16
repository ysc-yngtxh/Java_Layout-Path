package com.example.mvc.controller;

import com.example.mvc.entity.OrderItem;
import com.example.mvc.service.OrderMVCService;
import java.math.BigDecimal;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-22 下午11:30
 * @apiNote TODO
 */
@RestController
@RequiredArgsConstructor
public class OrderMVCController {

	private final OrderMVCService orderMVCService;

	@RequestMapping("/order")
	public void createOrder() {
		OrderItem orderItem = new OrderItem(1L, 1L, new BigDecimal("2"));
		orderMVCService.createOrder(1, 2, Collections.singletonList(orderItem));
	}
}
