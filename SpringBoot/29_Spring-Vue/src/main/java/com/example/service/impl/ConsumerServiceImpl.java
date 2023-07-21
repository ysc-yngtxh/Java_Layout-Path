package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dao.ConsumerDao;
import com.example.entity.Consumer;
import com.example.service.ConsumerService;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * (Consumer)表服务实现类
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
        Consumer consumer = consumerDao.selectOne(new LambdaQueryWrapper<Consumer>().eq(Consumer::getUsername, username));
        if (Objects.nonNull(consumer)) {
            return consumer.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public List<Consumer> queryPage(Integer page, Integer size) {
        List<Consumer> consumerList = consumerDao.selectList(null);
        List<List<Consumer>> partition = Lists.partition(consumerList, size);
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
