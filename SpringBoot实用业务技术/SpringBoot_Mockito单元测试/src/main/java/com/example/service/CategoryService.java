package com.example.service;

import com.example.mapper.CategoryMapper;
import com.example.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-26 21:12
 * @apiNote TODO
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public Category findCategoryById(Integer id) {
        Category category = categoryMapper.findById(id);
        return category;
    }
}
