package com.example.service;

import com.example.entity.EceUser;

/**
 * (User)表服务接口
 *
 * @author 游家纨绔
 * @since 2024-09-11 22:37:34
 */
public interface UserService {

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
     * @param user 实例对象
     * @return 实例对象
     */
    EceUser insert(EceUser user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    EceUser update(EceUser user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
