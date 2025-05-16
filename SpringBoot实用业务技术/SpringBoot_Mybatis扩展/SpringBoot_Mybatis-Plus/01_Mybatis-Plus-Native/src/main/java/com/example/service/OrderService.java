package com.example.service;

import com.example.entity.Order;
import java.util.List;
import java.util.Map;

/**
 * 订单详情表(Order)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-08-31 19:40:40
 */
public interface OrderService {

	Order selectById();

	List<Map<String, Object>> selectMaps();

	Integer updateByVersion();
}
