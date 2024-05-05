package com.example.service.impl;

import com.example.entity.User;
import com.example.dao.UserDao;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-07-22 08:45:21
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

}
