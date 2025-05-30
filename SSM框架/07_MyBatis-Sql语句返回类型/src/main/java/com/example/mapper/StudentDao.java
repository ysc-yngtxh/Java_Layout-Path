package com.example.mapper;

import com.example.pojo.Student;
import com.example.vo.MyStudent;
import com.example.vo.ViewStudent;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface StudentDao {

	ViewStudent selectStudentByView(@Param("sid") Integer id);

	// 定义方法返回Map
	Map<Object, Object> selectMapById(Integer id);

	// 使用resultMap定义映射关系
	List<Student> selectAllStudent();

	List<MyStudent> selectAliasCol();

	// 第一种模糊查询，在Java代码指定like的内容
	List<Student> selectLikeOne(String name);

	// name=李，在mapper中拼接 like "%" 李 "%"
	List<Student> selectLikeTwo(String name);

	// name=李，在mapper中使用concat函数拼接 like "%" 李 "%"
	List<Student> selectLikeThree(String name);
}
