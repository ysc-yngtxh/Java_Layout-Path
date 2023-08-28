package com.example.service;

import com.example.entity.TbUser;

import java.util.Map;

/**
 * (TbUser)表服务接口
 *
 * @author makejava
 * @since 2023-08-25 00:19:30
 */
public interface TbUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbUser queryById(Long id);

    /**
     * 通过ID查询单条数据,忽略 tenant_id 作为查询条件
     *
     * @param id 主键
     * @return 实例对象
     */
    TbUser queryByIdIgnoreTenant(Long id);

    Map<String, TbUser> query();

    /**
     * 新增数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    TbUser insert(TbUser tbUser);

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    TbUser update(TbUser tbUser);

    /**
     * 修改数据
     *
     * @param tbUser 实例对象
     * @return 实例对象
     */
    TbUser fullTableUpdate(TbUser tbUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
