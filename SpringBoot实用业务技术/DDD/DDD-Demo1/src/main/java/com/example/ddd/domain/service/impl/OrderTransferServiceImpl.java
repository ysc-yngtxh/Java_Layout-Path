package com.example.ddd.domain.service.impl;

import com.example.ddd.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO
 */
@Service
public class OrderTransferServiceImpl {

	private final OrderRepository orderRepository;

	public OrderTransferServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

}
