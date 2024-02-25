package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Tb2_Sku;
import com.example.mapper.Tb2_SkuMapper;
import com.example.service.Tb2_SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * (Tb2_Sku)表服务实现类
 * @author 游家纨绔
 * @since 2023-08-31 19:37:00
 */
@Service("tbSkuService")
@RequiredArgsConstructor
public class Tb2_SkuServiceImpl implements Tb2_SkuService {
    private final Tb2_SkuMapper tb2SkuMapper;

    /**
     * 根据Id集合查询。查询结果中menuList字段从数据库映射出来的是 List<String>类型
     */
    public Tb2_Sku selectById(Integer Id) {
        if (Objects.isNull(Id)) {
            return tb2SkuMapper.selectById(1);
        }
        return tb2SkuMapper.selectById(Id);
    }

    /**
     * 根据Id集合查询。查询结果为一个 Map 类型，且key 部分字段为表字段
     */
    public List<Map<String, Object>> selectMaps() {
        return tb2SkuMapper.selectMaps(new QueryWrapper<Tb2_Sku>().eq("id", 1));
    }

    /**
     * 删除 Id=1 的数据
     */
    public void deleteLogic() {
        tb2SkuMapper.deleteById(1);
    }
}