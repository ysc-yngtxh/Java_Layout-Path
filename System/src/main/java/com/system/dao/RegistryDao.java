package com.system.dao;

import com.system.pojo.Registry;
import org.springframework.stereotype.Repository;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/7 15:54
 */
@Repository
public interface RegistryDao {
    //通过用户id查找基本信息
    Registry selectUserId(int id);
    //通过用户名登录
    Registry selectUser(String username);

    //注册
    int insertUser(Registry registry);

}
