package com.example.service;

import com.example.entity.Brand;

import java.util.List;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系(Brand)表服务接口
 * @author 游家纨绔
 * @since 2023-09-02 14:48:25
 */
public interface BrandService {

    List<Brand> selectAll();

    Brand selectByName();

    Brand insertNameAndLetter();
}
