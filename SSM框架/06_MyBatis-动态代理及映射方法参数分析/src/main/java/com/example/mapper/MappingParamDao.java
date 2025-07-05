package com.example.mapper;

import com.example.pojo.Student;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface MappingParamDao {

	List<Student> selectMap(Map<String,Object> map);

	List<Student> selectStudents();

	List<Student> selectStudentsSingleParam(String name);

	// TODO 当SQL中需要传入多个参数的时候，要将where过滤条件字段对应好传参属性名，否则可能会出现字段值传递错误的情况。

	List<Student> selectStudentsPropertyParam(String name, Integer age);

	List<Student> selectStudentsReflect(String name, Integer age);

	List<Student> selectStudentsAnnotationParam(@Param("name") String name,
	                                            @Param("age") Integer age);

	int insertStudent(Student Student);
}
