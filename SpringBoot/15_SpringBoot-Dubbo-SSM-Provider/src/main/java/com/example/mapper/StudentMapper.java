package com.example.mapper;

import com.example.domain.Student;

/**
 * @author 游家纨绔
 */
public interface StudentMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Student record);

	int insertSelective(Student record);

	Student selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);

	// 获取学生总人数
	Integer selectAllStudentCount();
}
