package com.youshicheng.service;

import com.youshicheng.mapper.UserMapper;
import com.youshicheng.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author 游家纨绔
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;;

    public User queryById(Integer id){
        /*try {
            TimeUnit.SECONDS.sleep(2);   // 模拟用户访问超时，这个时候就触发了Hystrix的服务降级
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return userMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void insertUser(User user){
        userMapper.insert(user);
    }
}
