package com.example.service.impl;

import com.example.entity.Brand;
import com.example.mapper.BrandDao;
import com.example.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌表(Brand)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-08-18 23:15:50
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
