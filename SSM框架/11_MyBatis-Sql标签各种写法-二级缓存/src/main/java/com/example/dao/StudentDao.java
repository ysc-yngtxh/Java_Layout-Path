package com.example.dao;

import com.example.domain.Student;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cache.impl.PerpetualCache;

import java.util.List;

/**
 * @author 游家纨绔
 */
@CacheNamespace(implementation = PerpetualCache.class)
public interface StudentDao {

    // 动态SQL，使用Java对象作为参数
    List<Student> selectStudentIf(Student student);

    // where使用
    List<Student> selectStudentWhere(Student student);

    // foreach使用--用法1
    List<Student> selectForEachOne(List<Integer> idList);

    // foreach使用--用法2
    List<Student> selectForEachTwo(Integer[] stuArray);

    // foreach使用--用法3
    List<Student> selectForEachThree(@Param("stuList") List<Student> stuList);

    // 使用pageHelper分页数据
    List<Student> selectAll();

    // mybatis一级缓存
    Student selectIdOne(Integer id);

    // mybatis二级缓存
    Student selectIdTwo(Integer id);

    // mybatis二级缓存
    @ResultType(Student.class)
    @Select("select * from student where id=#{id}")
    Student selectIdThree(Integer id);
}
