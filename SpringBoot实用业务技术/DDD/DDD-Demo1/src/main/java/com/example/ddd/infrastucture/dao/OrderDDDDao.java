package com.example.ddd.infrastucture.dao;

import com.example.ddd.infrastucture.po.OrderPO;

/**
 * @author 游家纨绔
 * @Description TODO OrderDDD DAO层
 * @since 2024-04-18 07:20:00
 */
public interface OrderDDDDao {

	OrderPO findByOrderId(long orderId);

	int insert(OrderPO order);

	int updateByPrimaryKey(OrderPO order);
}
