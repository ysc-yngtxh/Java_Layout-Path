package com.example.service;

import com.example.mapper.CategoryMapper;
import com.example.mapper.SkuMapper;
import com.example.pojo.Category;
import com.example.pojo.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SkuMapper skuMapper;

    public String getStr(String str){
        return str;
    }

 	public void getVoid() {
        System.out.println("没有返回值");
    }

    public Category findProductById(Integer id) {
        Category category = categoryMapper.findById(id);
        return category;
    }

    public Sku findSkuById(Integer id) {
        Sku sku = skuMapper.findById(id);
        return sku;
    }
}
