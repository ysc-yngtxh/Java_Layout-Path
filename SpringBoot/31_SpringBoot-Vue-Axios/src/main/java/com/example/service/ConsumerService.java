package com.example.service;

import com.example.entity.TbConsumer;

import java.util.List;

/**
 * (TbConsumer)表服务接口
 *
 * @author makejava
 * @since 2023-07-09 09:16:57
 */
public interface ConsumerService {

    boolean check(String username, String password);

    List<TbConsumer> queryPage(Integer page, Integer size);

    Integer countAll();

}
