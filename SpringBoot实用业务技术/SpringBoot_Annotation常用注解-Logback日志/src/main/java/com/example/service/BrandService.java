package com.example.service;

import com.example.entity.Brand;

/**
 * 品牌表(Brand)表服务接口
 *
 * @author 游家纨绔
 * @since 2024-02-25 13:20:40
 */
public interface BrandService {

	/**
	 * 通过ID查询单条数据
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	Brand queryById(Long id);
}
