package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.RocketPoints;
import com.example.mapper.RocketPointsMapper;
import com.example.service.RocketPointsService;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 积分表(RocketPoints)表服务实现类
 */
@Service("rocketPointsService")
public class RocketPointsServiceImpl extends ServiceImpl<RocketPointsMapper, RocketPoints> implements RocketPointsService {}
