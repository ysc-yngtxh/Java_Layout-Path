package com.example.v3.mapper;

import com.example.v3.annotation.Param;
import com.example.v3.annotation.Select;
import com.example.v3.entity.Student;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-04 23:52
 * @apiNote TODO 自定义Mapper
 */
public interface StudentMapper3 {

    @Select("select * from student where name = #{name} and age = #{age}")
    public List<Student> queryUserReflect(String name, Integer age);

    @Select("select * from student where name = #{myName} and age = #{myAge}")
    public List<Student> queryUser(@Param("myName") String name, @Param("myAge") Integer age);

    @Select("select * from student where id = #{id}")
    public Student queryUserById(@Param("id") Integer id);

}
