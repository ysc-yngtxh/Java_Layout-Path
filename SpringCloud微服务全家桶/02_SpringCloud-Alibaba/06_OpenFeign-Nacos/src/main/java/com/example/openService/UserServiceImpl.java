package com.example.openService;

import com.example.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-18 10:20
 * @apiNote TODO
 */
@Component
public class UserServiceImpl implements UserService {

    @Override
    public User queryById(Integer id) {
        return User.builder().alias("不好意思嗷，熔断了！！！").build();
    }
}
