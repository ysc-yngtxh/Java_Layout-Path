package com.example.ddd.infrastucture.dao;

import com.example.ddd.domain.entity.Order;
import com.example.ddd.infrastucture.po.OrderPO;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO Order DAO层
 */
public interface OrderDao {
    OrderPO findByOrderId(long orderId);
    int insert(OrderPO order);
    int updateByPrimaryKey(OrderPO order);
}
