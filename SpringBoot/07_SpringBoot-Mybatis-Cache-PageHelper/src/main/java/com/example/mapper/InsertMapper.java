package com.example.mapper;

import com.example.pojo.Student;

/**
 * @author 游家纨绔
 */
public interface InsertMapper {

	int insert(Student record);

	int insertSelective(Student record);

}
