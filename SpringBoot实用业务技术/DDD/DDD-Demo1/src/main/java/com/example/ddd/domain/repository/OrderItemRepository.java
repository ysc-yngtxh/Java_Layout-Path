package com.example.ddd.domain.repository;

import com.example.ddd.domain.valobj.OrderItem;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-22 下午11:50
 * @apiNote TODO 订单商品仓库
 */
public interface OrderItemRepository {

	void save(List<OrderItem> orderItemList);
}
