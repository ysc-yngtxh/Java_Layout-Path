package com.example.springbootshirojwt.mapper;

import com.example.springbootshirojwt.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.BaseMapper;

public interface CategoryMapper extends BaseMapper<Category>, IdListMapper<Category,Long> {
}
