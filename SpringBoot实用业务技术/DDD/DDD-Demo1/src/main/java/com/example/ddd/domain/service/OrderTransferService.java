package com.example.ddd.domain.service;

import com.example.ddd.domain.entity.OrderDDD;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-23 上午7:00
 * @apiNote TODO
 */
public interface OrderTransferService {

	void transfer(OrderDDD sourceAccount, OrderDDD targetAccount);
}
