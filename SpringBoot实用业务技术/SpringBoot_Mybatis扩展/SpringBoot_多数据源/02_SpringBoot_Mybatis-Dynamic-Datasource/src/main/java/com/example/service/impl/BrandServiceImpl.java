package com.example.service.impl;

import com.example.entity.Brand;
import com.example.mapper.business2.BrandMapper;
import com.example.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系(Brand)表服务实现类
 * @author 游家纨绔
 * @since 2023-09-02 14:48:26
 */
@Service("brandService")
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandMapper brandMapper;

    @Override
    public List<Brand> selectAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand selectByName() {
        return brandMapper.selectByName("华为（HUAWEI）");
    }

    @Override
    public Brand insertNameAndLetter() {
        Brand brand = new Brand();
        brand.setName("华为mate60 pro");
        brand.setImage("https://consumer.huawei.com/content/dam/huawei-cbg-site/cn/mkt/pdp/phones/mate60-pro/img/performance/performance-feature-item-2@2x.webp");
        brand.setLetter("H");
        brandMapper.insertNameAndLetter(brand);
        return brand;
    }
}
