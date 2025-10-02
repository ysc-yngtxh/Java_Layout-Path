package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Brand;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-25 00:00
 * @apiNote TODO
 */
public interface BrandMapper extends BaseMapper<Brand> {

	Brand findById(Integer id);
}
