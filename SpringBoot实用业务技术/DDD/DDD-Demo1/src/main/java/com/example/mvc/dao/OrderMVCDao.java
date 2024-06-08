package com.example.mvc.dao;

import com.example.mvc.entity.OrderMVC;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @description TODO 订单DAO层
 */
public interface OrderMVCDao {
    OrderMVC find(long orderId);
    void insert(OrderMVC orderMVC);
    void updateByPrimaryKey(OrderMVC orderMVC);
}
