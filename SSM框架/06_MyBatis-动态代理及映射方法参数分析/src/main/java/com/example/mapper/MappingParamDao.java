package com.example.mapper;

import com.example.pojo.SSMStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MappingParamDao {

    List<SSMStudent> selectStudents();

    List<SSMStudent> selectStudentsSingleParam(String name);

    // TODO 当SQL中需要传入多个参数的时候，要将where过滤条件字段对应好传参属性名，否则可能会出现字段值传递错误的情况。

    List<SSMStudent> selectStudentsPropertyParam(String name, Integer age);

    List<SSMStudent> selectStudentsReflect(String name, Integer age);

    List<SSMStudent> selectStudentsAnnotationParam(@Param("name") String name,
                                                   @Param("age") Integer age);

    int insertStudent(SSMStudent SSMStudent);
}
