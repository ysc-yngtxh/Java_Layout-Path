package com.example.mvc.dao;

import com.example.mvc.entity.OrderItem;

import java.util.List;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @description TODO 订单商品DAO层
 */
public interface OrderItemMVCDao {
    void insert(OrderItem orderItem);
    void update(OrderItem orderItem);
    List<OrderItem> getOrderItems(long orderId);
}
