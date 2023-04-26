package com.cn.leyou.service;

import com.cn.leyou.mapper.CategoryMapper;
import com.cn.leyou.enums.ExceptionEnum;
import com.cn.leyou.exception.LyException;
import com.cn.leyou.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoryListByPid(Long pid) {
        //查询条件,mapper会把对象中的非空属性做为查询条件
        Category t = new Category();
        t.setParentId(pid);
        List<Category> list = categoryMapper.select(t);
        //判断结果
        //  list==null || list.isEmpty()可以使用工具类CollectionUtils.isEmpty(list)
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        return list;
    }

    public List<Category> queryByIds(List<Long> ids){
        //根据主键字符串进行查询，类中只有存在一个带有@Id注解的字段
        List<Category> list = categoryMapper.selectByIdList(ids);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.CATEGORY_NOT_FOND);
        }
        return list;
    }
}
