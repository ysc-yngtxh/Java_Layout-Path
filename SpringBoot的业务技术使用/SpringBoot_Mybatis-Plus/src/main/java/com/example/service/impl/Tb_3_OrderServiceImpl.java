package com.example.service.impl;

import com.example.entity.Tb_3_Order;
import com.example.mapper.Tb_3_OrderMapper;
import com.example.service.Tb_3_OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 订单详情表(Tb_3_Order)表服务实现类
 * @author 游家纨绔
 * @since 2023-08-31 19:36:39
 */
@Service("tbOrderService")
@RequiredArgsConstructor
public class Tb_3_OrderServiceImpl implements Tb_3_OrderService {
    private final Tb_3_OrderMapper tb3OrderMapper;

    public Tb_3_Order selectById() {
        return tb3OrderMapper.selectById(1);
    }
}