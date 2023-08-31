package com.example.service.impl;


import com.example.entity.TbSku;
import com.example.mapper.TbSkuMapper;
import com.example.service.TbSkuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * (TbSku)表服务实现类
 * @author 游家纨绔
 * @since 2023-08-31 19:37:00
 */
@Service("tbSkuService")
public class TbSkuServiceImpl implements TbSkuService {
    @Resource
    private TbSkuMapper tbSkuMapper;

    /**
     * 根据Id集合查询。查询结果中menuList字段从数据库映射出来的是 List<String>类型
     */
    public List<TbSku> selectBatchIds() {
        List<Integer> list = Arrays.asList(18, 56, 98, 35);
        return tbSkuMapper.selectBatchIds(list);
    }
}