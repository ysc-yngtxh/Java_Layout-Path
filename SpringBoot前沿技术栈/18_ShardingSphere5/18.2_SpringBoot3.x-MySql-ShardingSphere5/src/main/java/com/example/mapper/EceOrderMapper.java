package com.example.mapper;

import com.example.entity.EceOrder;

/**
 * 订单管理表(EceOrder)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2024-09-20 21:30:00
 */
public interface EceOrderMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    EceOrder queryById(Long orderId);

    /**
     * 新增数据
     *
     * @param eceOrder 实例对象
     * @return 影响行数
     */
    int insert(EceOrder eceOrder);

    /**
     * 修改数据
     *
     * @param eceOrder 实例对象
     * @return 影响行数
     */
    int update(EceOrder eceOrder);

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 影响行数
     */
    int deleteById(Long orderId);

}
