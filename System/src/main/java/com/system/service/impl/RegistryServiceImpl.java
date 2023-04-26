package com.system.service.impl;

import com.system.dao.RegistryDao;
import com.system.pojo.Courses;
import com.system.pojo.Registry;
import com.system.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/12 10:42
 */
@Service
public class RegistryServiceImpl implements RegistryService {
    @Autowired
    private RegistryDao registryDao;

    //根据用户id查找用户基本信息
    @Override
    public Registry selectUserId(int id) {
        Registry registry = registryDao.selectUserId(id);
        return registry;
    }

    //通过用户名查找用户，用来登录
    @Override
    public Registry selectUser(String username) {
        Registry registry = registryDao.selectUser(username);
        return registry;
    }

    //注册 新增
    @Override
    public int insertUser(Registry registry) {
        int num = registryDao.insertUser(registry);
        return num;
    }


}
