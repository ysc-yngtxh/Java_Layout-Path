package com.example.mapper;

import com.example.entity.EceLookupClassify;

/**
 * 字典表(EceLookup)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2024-09-17 23:59:31
 */
public interface EceLookupClassifyMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param lookupId 主键
     * @return 实例对象
     */
    EceLookupClassify queryById(Long lookupId);
    /**
     * 新增数据
     *
     * @param eceLookup 实例对象
     * @return 影响行数
     */
    int insert(EceLookupClassify eceLookup);

    /**
     * 修改数据
     *
     * @param eceLookup 实例对象
     * @return 影响行数
     */
    int update(EceLookupClassify eceLookup);

    /**
     * 通过主键删除数据
     *
     * @param lookupId 主键
     * @return 影响行数
     */
    int deleteById(Long lookupId);

}

