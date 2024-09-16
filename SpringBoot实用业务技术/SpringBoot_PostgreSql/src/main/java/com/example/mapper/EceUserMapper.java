package com.example.mapper;

import com.example.entity.EceUser;

/**
 * (EceUser)表数据库访问层
 *
 * @author makejava
 * @since 2024-09-16 13:12:14
 */
public interface EceUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EceUser queryById(Integer id);

    /**
     * 新增数据
     *
     * @param eceuser 实例对象
     * @return 影响行数
     */
    int insert(EceUser eceuser);
}

