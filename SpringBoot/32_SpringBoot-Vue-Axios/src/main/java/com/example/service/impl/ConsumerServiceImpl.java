package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.User;
import com.example.mapper.ConsumerDao;
import com.example.service.ConsumerService;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2023-07-09 09:16:58
 */
@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService {

    @Resource
    private ConsumerDao consumerDao;

    @Override
    public boolean check(String username, String password) {
        User user = consumerDao.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
        if (Objects.nonNull(user)) {
            return user.getPassWord().equals(password);
        }
        return false;
    }

    @Override
    public List<User> queryPage(Integer page, Integer size) {
        List<User> userList = consumerDao.selectList(null);
        List<List<User>> partition = Lists.partition(userList, size);
        if (partition.size() < page) {
            return Collections.emptyList();
        }
        return partition.get(page - 1);
    }

    @Override
    public Integer countAll() {
        Long aLong = consumerDao.selectCount(null);
        return aLong.intValue();
    }
}
