package com.example.service;

import com.example.mapper.SkuMapper;
import com.example.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-26 21:10:00
 * @apiNote TODO
 */
@Service
public class SkuService {

	@Autowired
	private SkuMapper skuMapper;

	public Sku findSkuById(Integer id) {
		Sku sku = skuMapper.findById(id);
		return sku;
	}
}
