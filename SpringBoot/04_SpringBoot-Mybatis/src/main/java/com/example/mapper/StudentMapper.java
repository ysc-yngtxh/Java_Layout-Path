package com.example.mapper;

import com.example.domain.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 游家纨绔
 */
@Mapper // 扫描mapper接口到spring容器
public interface StudentMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Student record);

	int insertSelective(Student record);

	Student selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);
}
