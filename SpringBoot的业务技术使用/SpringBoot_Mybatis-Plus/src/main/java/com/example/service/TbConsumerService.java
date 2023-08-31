package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.TbConsumer;

import java.util.List;
import java.util.Map;

/**
 * (TbConsumer)表服务接口
 * @author 游家纨绔
 * @since 2023-08-28 22:28:21
 */
public interface TbConsumerService {

    // TODO 第一部分：查询演示
    TbConsumer selectById();
    List<TbConsumer> selectByIds();
    List<TbConsumer> selectByMap();
    List<Map<String, Object>> selectMaps();
    List<TbConsumer> selectByWrapper1();
    List<TbConsumer> selectByWrapper2();
    List<TbConsumer> selectByWrapper3();
    List<TbConsumer> selectByWrapper4();
    List<TbConsumer> selectByWrapper5();
    List<TbConsumer> selectByWrapper6();
    List<TbConsumer> selectByWrapper7();
    List<TbConsumer> selectByWrapper8();
    List<TbConsumer> selectByWrapper9();
    List<TbConsumer> selectByWrapper10();
    List<TbConsumer> selectByWrapper11();

    // TODO 第二部分：高级查询
    List<TbConsumer> condition(String name, String email);
    List<TbConsumer> selectByWrapperEntity();
    List<TbConsumer> selectByWrapperAllEq();
    List<Map<String, Object>> selectByWrapperMap();
    Map<String, List<TbConsumer>> selectCustomAnnotation();
    List<TbConsumer> selectLambdaQuery();

    // TODO 第三部分：分页查询
    Page<TbConsumer> selectPage();

    // TODO 第四部分：更新操作
    void updateConsumer();

    // TODO 第五部分：删除操作
    void  deleteConsumer();

    // TODO 第五部分：插入操作
    TbConsumer insertConsumer();
}

