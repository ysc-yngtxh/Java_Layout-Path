package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author 游家纨绔
 */
@Mapper // 扫描mapper接口到Spring容器
public interface DeleteMapper {

	int deleteByPrimaryKey(Integer id);

}
