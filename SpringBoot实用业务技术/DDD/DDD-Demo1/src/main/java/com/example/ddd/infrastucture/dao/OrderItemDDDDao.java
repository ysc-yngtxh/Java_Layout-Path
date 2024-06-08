package com.example.ddd.infrastucture.dao;

import com.example.ddd.infrastucture.po.OrderItemPO;

import java.util.List;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO OrderItem DAO层
 */
public interface OrderItemDDDDao {
    int insert(OrderItemPO orderItem);
    int batchInsert(List<OrderItemPO> orderItem);
    int update(OrderItemPO orderItem);
    List<OrderItemPO> getOrderItems(long orderId);
}