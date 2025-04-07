package com.example.mapper;

import com.example.pojo.SSMStudent;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 游家纨绔
 */
// @CacheNamespace(implementation = PerpetualCache.class)
public interface StudentDao {

    // <if>标签使用。动态SQL，使用Java对象作为参数
    List<SSMStudent> selectStudentIf(SSMStudent SSMStudent);

    // <where>标签使用
    List<SSMStudent> selectStudentWhere(SSMStudent SSMStudent);

    // <trim>标签使用
    List<SSMStudent> selectStudentTrim(SSMStudent SSMStudent);

    // <choose>标签使用
    List<SSMStudent> selectStudentChoose(SSMStudent SSMStudent);

    // <foreach>标签--用法1
    List<SSMStudent> selectForEachOne(List<Integer> idList);

    // <foreach>标签--用法2
    List<SSMStudent> selectForEachTwo(Integer[] stuArray);

    // <foreach>标签--用法3
    List<SSMStudent> selectForEachThree(@Param("stuList") List<SSMStudent> stuList);

    // 使用pageHelper分页数据
    List<SSMStudent> selectAll();

    // Mybatis一级缓存
    SSMStudent selectIdOne(Integer id);

    // Mybatis二级缓存（XML配置）
    SSMStudent selectIdTwo(Integer id);

    // Mybatis二级缓存（注解配置）
    @ResultType(SSMStudent.class)
    @Select("select * from ssm_student where id=#{id}")
    SSMStudent selectIdThree(Integer id);
}
