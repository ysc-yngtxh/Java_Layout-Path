package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.RocketOrder;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 订单表(RocketOrder)表服务接口
 */
public interface RocketOrderService extends IService<RocketOrder> {

    RocketOrder addOder(RocketOrder order);
}
