package com.example.mapper;

import com.example.entity.EceUser;

/**
 * (EceUser)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2024-09-11 22:30:30
 */
public interface EceUserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param eceId 主键
     * @return 实例对象
     */
    EceUser queryById(Integer eceId);

    /**
     * 新增数据
     *
     * @param eceUser 实例对象
     * @return 影响行数
     */
    int insert(EceUser eceUser);


    /**
     * 修改数据
     *
     * @param eceUser 实例对象
     * @return 影响行数
     */
    int update(EceUser eceUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
