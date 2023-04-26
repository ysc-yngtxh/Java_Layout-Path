package com.cn.leyou.mapper;

import com.cn.leyou.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.BaseMapper;

public interface CategoryMapper extends BaseMapper<Category>, IdListMapper<Category,Long> {
}
