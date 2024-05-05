package com.example.ddd.application.service;

import com.example.ddd.domain.valobj.Address;
import com.example.ddd.domain.valobj.OrderItem;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-22 下午11:42
 * @apiNote TODO
 */
public interface IOrderService {
    void createOrder(int buyerId, int sellerId, Set<OrderItem> orderItems);
    void updateAddress(long orderId, Address address);
    void setDiscount(long orderId, BigDecimal discountAmount);
}
