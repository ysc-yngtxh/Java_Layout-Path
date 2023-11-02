package com.example.openService;

import com.example.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-02 12:37
 * @apiNote TODO 降级服务
 */
@Component
public class UserServiceImpl implements UserService{

    @Override
    public User queryById(Integer id) {
        User user = new User();
        user.setBackup("你好，服务已降级处理！！！");
        return user;
    }
}
