package com.example.service.impl;

import com.example.dao.BrandDao;
import com.example.entity.Brand;
import com.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌表(Brand)表服务实现类
 *
 * @author makejava
 * @since 2023-08-18 23:15:53
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public Brand getOne(Integer id) {
        Brand brand = brandDao.selectById(id);
        return brand;
    }
}

