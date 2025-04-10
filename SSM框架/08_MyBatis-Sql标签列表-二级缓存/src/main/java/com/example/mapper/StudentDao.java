package com.example.mapper;

import com.example.pojo.Student;
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
    List<Student> selectStudentIf(Student Student);

    // <where>标签使用
    List<Student> selectStudentWhere(Student Student);

    // <trim>标签使用
    List<Student> selectStudentTrim(Student Student);

    // <choose>标签使用
    List<Student> selectStudentChoose(Student Student);

    // <foreach>标签--用法1
    List<Student> selectForEachOne(List<Integer> idList);

    // <foreach>标签--用法2
    List<Student> selectForEachTwo(Integer[] stuArray);

    // <foreach>标签--用法3
    List<Student> selectForEachThree(@Param("stuList") List<Student> stuList);

    // 使用pageHelper分页数据
    List<Student> selectAll();

    // Mybatis一级缓存
    Student selectIdOne(Integer id);

    // Mybatis二级缓存（XML配置）
    Student selectIdTwo(Integer id);

    // Mybatis二级缓存（注解配置）
    @ResultType(Student.class)
    @Select("select * from ssm_student where id=#{id}")
    Student selectIdThree(Integer id);
}
