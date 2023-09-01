package com.example.service;

import com.example.entity.Tb_3_Order;

import java.util.List;
import java.util.Map;

/**
 * 订单详情表(Tb_3_Order)表服务接口
 * @author 游家纨绔
 * @since 2023-08-31 19:36:39
 */
public interface Tb_3_OrderService {
    Tb_3_Order selectById();
    List<Map<String, Object>> selectMaps();
    Integer updateByVersion();
}
