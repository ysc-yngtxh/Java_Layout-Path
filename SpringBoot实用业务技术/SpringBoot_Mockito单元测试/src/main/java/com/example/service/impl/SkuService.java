package com.example.service.impl;

import com.example.mapper.SkuMapper;
import com.example.pojo.Sku;
import com.example.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-26 21:10:00
 * @apiNote TODO
 */
@Service
public class SkuService implements AuditService<Sku> {

	@Autowired
	private SkuMapper skuMapper;

	@Override
	public Sku findAllById(Integer id) {
		return skuMapper.findById(id);
	}
}
