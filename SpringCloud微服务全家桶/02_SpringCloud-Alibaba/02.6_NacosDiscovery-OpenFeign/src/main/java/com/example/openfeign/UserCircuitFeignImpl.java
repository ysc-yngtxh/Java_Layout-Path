package com.example.openfeign;

import com.example.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-18 10:20
 * @apiNote TODO
 */
@Component
public class UserCircuitFeignImpl implements UserCircuitFeign {

    @Override
    public User queryById(Integer id) {
        return User.builder().alias("不好意思嗷，熔断了！！！").build();
    }
}
