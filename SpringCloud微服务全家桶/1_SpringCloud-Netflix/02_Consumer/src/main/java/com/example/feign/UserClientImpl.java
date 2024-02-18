package com.example.feign;

import com.example.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 */
@Component  // 注入spring容器
public class UserClientImpl implements UserClient {

    @Override
    public User queryByIdLL(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUserName("未知用户");
        return user;
    }
}
