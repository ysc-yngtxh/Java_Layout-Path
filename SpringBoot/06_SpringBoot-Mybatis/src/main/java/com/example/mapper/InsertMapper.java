package com.example.mapper;

import com.example.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 游家纨绔
 */
@Mapper // 扫描mapper接口到Spring容器
public interface InsertMapper {

	int insert(Student record);

	int insertSelective(Student record);

}
