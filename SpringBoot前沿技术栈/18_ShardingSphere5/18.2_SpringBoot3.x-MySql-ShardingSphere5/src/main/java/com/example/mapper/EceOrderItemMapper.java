package com.example.mapper;

import com.example.entity.EceOrderItem;

/**
 * 订单商品项表(EceOrderItem)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2024-09-20 21:30:00
 */
public interface EceOrderItemMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param orderItemId 主键
     * @return 实例对象
     */
    EceOrderItem queryById(Long orderItemId);

    /**
     * 新增数据
     *
     * @param eceOrderItem 实例对象
     * @return 影响行数
     */
    int insert(EceOrderItem eceOrderItem);

    /**
     * 修改数据
     *
     * @param eceOrderItem 实例对象
     * @return 影响行数
     */
    int update(EceOrderItem eceOrderItem);

    /**
     * 通过主键删除数据
     *
     * @param orderItemId 主键
     * @return 影响行数
     */
    int deleteById(Long orderItemId);

}
