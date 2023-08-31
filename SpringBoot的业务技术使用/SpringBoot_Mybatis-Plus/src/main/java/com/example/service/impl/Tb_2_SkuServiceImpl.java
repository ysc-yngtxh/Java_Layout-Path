package com.example.service.impl;

import com.example.entity.Tb_2_Sku;
import com.example.mapper.Tb_2_SkuMapper;
import com.example.service.Tb_2_SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * (Tb_2_Sku)表服务实现类
 * @author 游家纨绔
 * @since 2023-08-31 19:37:00
 */
@Service("tbSkuService")
@RequiredArgsConstructor
public class Tb_2_SkuServiceImpl implements Tb_2_SkuService {
    private final Tb_2_SkuMapper tb2SkuMapper;

    /**
     * 根据Id集合查询。查询结果中menuList字段从数据库映射出来的是 List<String>类型
     */
    public Tb_2_Sku selectById() {
        return tb2SkuMapper.selectById(1);
    }

    public void deleteLogic() {
        tb2SkuMapper.deleteById(1);
    }
}