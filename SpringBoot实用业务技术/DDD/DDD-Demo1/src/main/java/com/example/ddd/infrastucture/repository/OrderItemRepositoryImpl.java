package com.example.ddd.infrastucture.repository;

import com.example.ddd.domain.repository.OrderItemRepository;
import com.example.ddd.domain.valobj.OrderItem;
import com.example.ddd.infrastucture.convertor.OrderItemToOrderItemPO;
import com.example.ddd.infrastucture.dao.OrderItemDDDDao;
import com.example.ddd.infrastucture.po.OrderItemPO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-22 下午11:50
 * @apiNote TODO
 */
@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

	@Autowired
	private OrderItemDDDDao orderItemDDDDao;
	@Autowired
	private OrderItemToOrderItemPO orderItemToOrderItemPO;

	@Override
	public void save(List<OrderItem> orderItemList) {
		List<OrderItemPO> orderItemPOList = orderItemList.stream()
		                                                 .map(orderItemToOrderItemPO::toOrderItemPO)
		                                                 .collect(Collectors.toList());

		// orderItemDDDDao => 存储订单商品数据
		orderItemDDDDao.batchInsert(orderItemPOList);
	}
}
