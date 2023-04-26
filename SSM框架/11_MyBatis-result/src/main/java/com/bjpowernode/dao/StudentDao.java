package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;
import com.bjpowernode.vo.MyStudent;
import com.bjpowernode.vo.ViewStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {


    ViewStudent selectStudentByView(@Param("sid") Integer id);

    //定义方法返回Map
    Map<Object,Object> selectMapById(Integer id);

    //使用resultMap定义映射关系
    List<Student> selectAllStudent();

    List<MyStudent> selectDiffcol();

    //第一种模糊查询，在Java代码指定like的内容
    List<Student> selectLikeOne(String name);

    //name就是李值，在mapper中拼接 like "%" 李 "%"
    List<Student> selectLikeTwo(String name);
}
