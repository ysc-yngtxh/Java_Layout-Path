package com.example.service.impl;

import com.example.entity.Brand;
import com.example.mapper.BrandDao;
import com.example.service.BrandService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 品牌表(Brand)表服务实现类
 *
 * @author 游家纨绔
 * @since 2024-02-25 13:20:40
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService {

	@Resource
	private BrandDao brandDao;

	/**
	 * 通过ID查询单条数据
	 *
	 * @param id 主键
	 * @return 实例对象
	 */
	@Override
	public Brand queryById(Long id) {
		return this.brandDao.queryById(id);
	}
}
