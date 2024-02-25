package com.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Brand;

/**
 * 品牌表(Brand)表数据库访问层
 *
 * @author makejava
 * @since 2024-02-25 13:19:36
 */
public interface BrandDao extends BaseMapper<Brand> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Brand queryById(Long id);
}

