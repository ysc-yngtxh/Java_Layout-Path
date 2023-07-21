package com.example.service;

import com.example.entity.Consumer;

import java.util.List;

/**
 * (Consumer)表服务接口
 *
 * @author makejava
 * @since 2023-07-09 09:16:57
 */
public interface ConsumerService {

    boolean check(String username, String password);

    List<Consumer> queryPage(Integer page, Integer size);

    Integer countAll();

}
