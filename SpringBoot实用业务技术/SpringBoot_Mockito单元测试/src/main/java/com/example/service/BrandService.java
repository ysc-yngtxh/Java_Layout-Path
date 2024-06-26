package com.example.service;

import com.example.mapper.BrandMapper;
import com.example.pojo.Brand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 品牌表(Brand)表服务实现类
 *
 * @author 游家纨绔
 * @since 2024-06-26 22:10:11
 */
@Service
public class BrandService {

    @Resource
    private BrandMapper brandMapper;

    public Brand findBrandById(Integer id) {
        Brand brand = brandMapper.findById(id);
        return brand;
    }
}
