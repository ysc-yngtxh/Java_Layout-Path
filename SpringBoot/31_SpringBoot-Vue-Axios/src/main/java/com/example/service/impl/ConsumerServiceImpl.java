package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mapper.ConsumerDao;
import com.example.entity.TbConsumer;
import com.example.service.ConsumerService;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * (TbConsumer)表服务实现类
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
        TbConsumer tbConsumer = consumerDao.selectOne(new LambdaQueryWrapper<TbConsumer>().eq(TbConsumer::getUserName, username));
        if (Objects.nonNull(tbConsumer)) {
            return tbConsumer.getPassWord().equals(password);
        }
        return false;
    }

    @Override
    public List<TbConsumer> queryPage(Integer page, Integer size) {
        List<TbConsumer> tbConsumerList = consumerDao.selectList(null);
        List<List<TbConsumer>> partition = Lists.partition(tbConsumerList, size);
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
