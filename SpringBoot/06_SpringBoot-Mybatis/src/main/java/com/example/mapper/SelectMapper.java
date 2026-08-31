package com.example.mapper;

import com.example.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 游家纨绔
 */
@Mapper // 扫描mapper接口到Spring容器
public interface SelectMapper {

	Student selectByPrimaryKey(Integer id);

    List<Student> selectByMap(Map<String, Object> map);

    List<Student> selectByObject(Student student);

    List<Student> selectStudentsSingleParam(String name);

    List<Student> selectStudentsPropertyParam(String name, Integer age);

    List<Student> selectStudentsReflect(String name, Integer age);

    List<Student> selectStudentsAnnotationParam(
            @Param("name") String name,
            @Param("age") Integer age
    );

}
