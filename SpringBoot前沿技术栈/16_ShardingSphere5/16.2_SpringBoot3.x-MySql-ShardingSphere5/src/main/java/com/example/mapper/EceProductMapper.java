package com.example.mapper;

import com.example.entity.EceProduct;

/**
 * 产品管理表(EceProduct)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2024-09-20 21:30:00
 */
public interface EceProductMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param productId 主键
     * @return 实例对象
     */
    EceProduct queryById(Long productId);

    /**
     * 新增数据
     *
     * @param eceProduct 实例对象
     * @return 影响行数
     */
    int insert(EceProduct eceProduct);

    /**
     * 修改数据
     *
     * @param eceProduct 实例对象
     * @return 影响行数
     */
    int update(EceProduct eceProduct);

    /**
     * 通过主键删除数据
     *
     * @param productId 主键
     * @return 影响行数
     */
    int deleteById(Long productId);

}
