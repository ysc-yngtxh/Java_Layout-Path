package com.example.mapper;

import com.example.entity.EceUser;

/**
 * (User)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2024-09-11 22:30:00
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
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(EceUser user);


    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(EceUser user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
