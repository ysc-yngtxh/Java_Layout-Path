package com.example.mapper;

import com.example.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 游家纨绔
 */
@Mapper // 扫描mapper接口到Spring容器
public interface SelectTagMapper {

    // <if>标签使用 -- 动态SQL，使用Java对象作为参数
    List<Student> selectStudentIfTag(String name, Integer age);

    // <where>标签使用
    List<Student> selectStudentWhereTag(String name, Integer age);

    // <trim>标签使用
    List<Student> selectStudentTrimTag(String name, Integer age, String email);

    // <choose>标签使用
    List<Student> selectStudentChooseTag(String name, Integer age, String email);

    // <foreach>标签 -- List<Integer>作为参数
    List<Student> selectForListForeachTag(List<Integer> idList);

    // <foreach>标签 -- Integer[]作为参数
    List<Student> selectForArrayForeachTag(Integer[] stuArray);

    // <foreach>标签 -- List<Student>作为参数
    List<Student> selectForObjectForeachTag(@Param("stuList") List<Student> stuList);

}
