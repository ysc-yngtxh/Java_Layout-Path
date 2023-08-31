package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.TbOrder;
import com.example.mapper.TbOrderMapper;
import com.example.service.TbOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单详情表(TbOrder)表服务实现类
 * @author 游家纨绔
 * @since 2023-08-31 19:36:39
 */
@Service("tbOrderService")
@RequiredArgsConstructor
public class TbOrderServiceImpl implements TbOrderService {
    private final TbOrderMapper tbOrderMapper;

    public TbOrder selectById() {
        return tbOrderMapper.selectById(1);
    }
}