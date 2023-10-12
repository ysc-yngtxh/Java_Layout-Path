package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Tb_1_Consumer;

import java.util.List;
import java.util.Map;

/**
 * (Tb_1_Consumer)表服务接口
 * @author 游家纨绔
 * @since 2023-08-28 22:28:21
 */
public interface Tb_1_ConsumerService {

    // TODO 第一部分：查询演示
    Tb_1_Consumer selectById();
    List<Tb_1_Consumer> selectByIds();
    List<Tb_1_Consumer> selectByMap();
    List<Map<String, Object>> selectMaps();
    List<Tb_1_Consumer> selectByWrapper1();
    List<Tb_1_Consumer> selectByWrapper2();
    List<Tb_1_Consumer> selectByWrapper3();
    List<Tb_1_Consumer> selectByWrapper4();
    List<Tb_1_Consumer> selectByWrapper5();
    List<Tb_1_Consumer> selectByWrapper6();
    List<Tb_1_Consumer> selectByWrapper7();
    List<Tb_1_Consumer> selectByWrapper8();
    List<Tb_1_Consumer> selectByWrapper9();
    List<Tb_1_Consumer> selectByWrapper10();
    List<Tb_1_Consumer> selectByWrapper11();

    // TODO 第二部分：高级查询
    List<Tb_1_Consumer> condition(String name, String email);
    List<Tb_1_Consumer> selectByWrapperEntity();
    List<Tb_1_Consumer> selectByWrapperAllEq();
    List<Map<String, Object>> selectByWrapperMap();
    Map<String, List<Tb_1_Consumer>> selectCustomAnnotation();
    List<Tb_1_Consumer> selectLambdaQuery();

    // TODO 第三部分：分页查询
    Page<Tb_1_Consumer> selectPage();

    // TODO 第四部分：更新操作
    void updateConsumer();

    // TODO 第五部分：删除操作
    void  deleteConsumer();

    // TODO 第五部分：插入操作
    Tb_1_Consumer insertConsumer();
}

