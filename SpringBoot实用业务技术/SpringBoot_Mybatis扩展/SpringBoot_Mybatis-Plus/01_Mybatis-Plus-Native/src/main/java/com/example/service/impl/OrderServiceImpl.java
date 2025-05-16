package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Order;
import com.example.mapper.OrderMapper;
import com.example.service.OrderService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 订单详情表(Order)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-08-31 19:40:40
 */
@Service("orderService")
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderMapper orderMapper;

	public Order selectById() {
		return orderMapper.selectById(1);
	}

	public List<Map<String, Object>> selectMaps() {
		return orderMapper.selectMaps(new QueryWrapper<Order>().eq("order_id", 1));
	}

	public Integer updateByVersion() {
		// 进行更新操作时候，我们需要通过 version字段作为乐观锁。
		// 那么我们就要先查出数据获取该数据的version值。在做完更新操作后，这个version值会进行一个自增形成一个新的version值。
		// 但是如果在我们获取version值到我们准备更新数据的这段时间里，有另一个线程先完成了获取数据并更新数据，那么我们的这条更新操作就会失败
		// 因为我们当前获取的 version值已经过时了，旧的 version 作为 where 条件已经找不到相关数据，自然也就更新失败
		Order tb3Order = orderMapper.selectById(1);
		tb3Order.setSkuId(123L);
		tb3Order.setNum(12);
		tb3Order.setBuyPrice(1.23);
		tb3Order.setMenuList(List.of("vip1", "vip2"));
		return orderMapper.updateById(tb3Order);
	}
}
