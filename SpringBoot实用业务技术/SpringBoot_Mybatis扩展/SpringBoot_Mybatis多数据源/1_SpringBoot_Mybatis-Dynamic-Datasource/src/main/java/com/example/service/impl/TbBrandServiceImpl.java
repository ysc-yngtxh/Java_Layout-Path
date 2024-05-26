package com.example.service.impl;

import com.example.entity.TbBrand;
import com.example.mapper.yun6.TbBrandMapper;
import com.example.service.TbBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系(TbBrand)表服务实现类
 * @author 游家纨绔
 * @since 2023-09-02 14:48:26
 */
@Service("tbBrandService")
@RequiredArgsConstructor
public class TbBrandServiceImpl implements TbBrandService {

    private final TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> selectAll() {
        return tbBrandMapper.selectAll();
    }

    @Override
    public TbBrand selectByName() {
        return tbBrandMapper.selectByName("华为mate60 pro");
    }

    @Override
    public TbBrand insertNameAndLetter() {
        TbBrand brand = new TbBrand();
        brand.setName("华为mate60 pro");
        brand.setImage("https://consumer.huawei.com/content/dam/huawei-cbg-site/cn/mkt/pdp/phones/mate60-pro/img/performance/performance-feature-item-2@2x.webp");
        brand.setLetter("H");
        tbBrandMapper.insertNameAndLetter(brand);
        return brand;
    }
}