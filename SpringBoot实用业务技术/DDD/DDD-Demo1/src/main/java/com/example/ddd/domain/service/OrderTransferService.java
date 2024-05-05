package com.example.ddd.domain.service;

import com.example.ddd.domain.entity.Order;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-23 上午7:10
 * @apiNote TODO
 */
public interface OrderTransferService {
    void transfer(Order sourceAccount, Order targetAccount);
}
