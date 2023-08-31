package com.example.service.impl;


import com.example.mapper.TbSkuMapper;
import com.example.service.TbSkuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (TbSku)表服务实现类
 *
 * @author makejava
 * @since 2023-08-31 19:37:00
 */
@Service("tbSkuService")
public class TbSkuServiceImpl implements TbSkuService {
    @Resource
    private TbSkuMapper tbSkuMapper;
}