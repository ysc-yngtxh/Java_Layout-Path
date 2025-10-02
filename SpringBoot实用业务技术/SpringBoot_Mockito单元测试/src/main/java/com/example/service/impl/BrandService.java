package com.example.service.impl;

import com.example.mapper.BrandMapper;
import com.example.pojo.Brand;
import com.example.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌表(Brand)表服务实现类
 *
 * @author 游家纨绔
 * @since 2024-06-26 22:10:10
 */
@Service
public class BrandService implements AuditService<Brand> {

	@Autowired
	private BrandMapper brandMapper;

	@Override
	public Brand findAllById(Integer id) {
		return brandMapper.findById(id);
	}

	public Brand add(Brand brand) {
		brandMapper.insert(brand);
		return brand;
	}
}
