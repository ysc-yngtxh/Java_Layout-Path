package com.example.service.impl;

import com.example.entity.EceUser;
import com.example.mapper.EceUserMapper;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author 游家纨绔
 * @since 2024-09-11 22:37:34
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private EceUserMapper eceUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public EceUser queryById(Integer id) {
        return this.eceUserMapper.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public EceUser insert(EceUser user) {
        this.eceUserMapper.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public EceUser update(EceUser user) {
        // this.eceUserMapper.update(user);
        // return this.queryById(user.getId());
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.eceUserMapper.deleteById(id) > 0;
    }
}
