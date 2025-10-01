package com.example.service.impl;

import com.example.mapper.CategoryMapper;
import com.example.pojo.Category;
import com.example.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-26 21:10
 * @apiNote TODO
 */
@Service
public class CategoryService implements AuditService<Category> {

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public Category findAllById(Integer id) {
		return categoryMapper.findById(id);
	}
}
