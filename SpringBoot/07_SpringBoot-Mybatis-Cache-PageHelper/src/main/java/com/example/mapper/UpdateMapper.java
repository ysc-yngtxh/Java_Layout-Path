package com.example.mapper;

import com.example.pojo.Student;

/**
 * @author 游家纨绔
 */
public interface UpdateMapper {

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);
}
