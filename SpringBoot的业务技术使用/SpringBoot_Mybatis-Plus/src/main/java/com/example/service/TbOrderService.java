package com.example.service;

import com.example.entity.TbOrder;

import java.util.List;

/**
 * 订单详情表(TbOrder)表服务接口
 * @author 游家纨绔
 * @since 2023-08-31 19:36:39
 */
public interface TbOrderService {
    List<TbOrder> selectByOrderId();
}
