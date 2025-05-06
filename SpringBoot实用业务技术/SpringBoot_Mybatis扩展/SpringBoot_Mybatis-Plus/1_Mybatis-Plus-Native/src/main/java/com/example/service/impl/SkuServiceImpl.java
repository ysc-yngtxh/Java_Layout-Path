package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Sku;
import com.example.mapper.SkuMapper;
import com.example.service.SkuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * (Sku)表服务实现类
 * @author 游家纨绔
 * @since 2023-08-31 19:37:00
 */
@Service("skuService")
@RequiredArgsConstructor
public class SkuServiceImpl implements SkuService {

    private final SkuMapper skuMapper;

    /**
     * 根据Id集合查询。查询结果中menuList字段从数据库映射出来的是 List<String>类型
     */
    public Sku selectById(Integer Id) {
        if (Objects.isNull(Id)) {
            return skuMapper.selectById(1);
        }
        return skuMapper.selectById(Id);
    }

    /**
     * 根据Id集合查询。查询结果为一个 Map 类型，且key 部分字段为表字段
     */
    public List<Map<String, Object>> selectMaps() {
        return skuMapper.selectMaps(new QueryWrapper<Sku>().eq("id", 1));
    }

    /**
     * 删除 Id=1 的数据
     */
    public void deleteLogic() {
        skuMapper.deleteById(1);
    }
}
