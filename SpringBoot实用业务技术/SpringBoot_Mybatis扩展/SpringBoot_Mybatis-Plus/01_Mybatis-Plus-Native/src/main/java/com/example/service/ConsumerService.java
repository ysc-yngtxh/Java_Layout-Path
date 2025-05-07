package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Consumer;

import java.util.List;
import java.util.Map;

/**
 * (Consumer)表服务接口
 * @author 游家纨绔
 * @since 2023-08-28 22:28:21
 */
public interface ConsumerService {

    // TODO 第一部分：查询演示
    Consumer selectById();
    List<Consumer> selectByIds();
    List<Consumer> selectByMap();
    List<Map<String, Object>> selectMaps();
    List<Consumer> selectByWrapper1();
    List<Consumer> selectByWrapper2();
    List<Consumer> selectByWrapper3();
    List<Consumer> selectByWrapper4();
    List<Consumer> selectByWrapper5();
    List<Consumer> selectByWrapper6();
    List<Consumer> selectByWrapper7();
    List<Consumer> selectByWrapper8();
    List<Consumer> selectByWrapper9();
    List<Consumer> selectByWrapper10();
    List<Consumer> selectByWrapper11();

    // TODO 第二部分：高级查询
    List<Consumer> condition(String name, String email);
    List<Consumer> selectByWrapperEntity();
    List<Consumer> selectByWrapperAllEq();
    List<Map<String, Object>> selectByWrapperMap();
    Map<String, List<Consumer>> selectCustomAnnotation();
    List<Consumer> selectLambdaQuery();

    // TODO 第三部分：分页查询
    Page<Consumer> selectPage();

    // TODO 第四部分：更新操作
    void updateConsumer();

    // TODO 第五部分：删除操作
    void  deleteConsumer();

    // TODO 第五部分：插入操作
    Consumer insertConsumer();
}
