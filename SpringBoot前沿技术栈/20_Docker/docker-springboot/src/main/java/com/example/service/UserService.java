package com.example.service;

import com.example.entity.User;

/**
 * (User)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-07-22 08:45:20
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);
}
