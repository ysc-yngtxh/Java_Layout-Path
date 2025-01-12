package com.example.mapper;

import com.example.pojo.Student;

import java.util.List;

/**
 * @author 游家纨绔
 */
public interface MappingTypeDao {

    /**
     * 一个简单类型的参数：单个参数，mybatis可以直接传入，不会出现字段值不符情况
     */
    Student selectStudentsType(Integer id);


    /**
     * 多个参数：使用Java对象作为接口中方法的参数
     */
    List<Student> selectMultiObject(Student student);
}
