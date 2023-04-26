package com.example.dao;

import com.example.domain.Student;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    // 返回一个key为name值的Map集合
    @MapKey("name")
    Map<String, Student> selectByPrimaryKeyMap(Integer id);

    // 返回一个key为name值的Map集合,区别于上一条查询语句，即xml文件中返回值不同就会导致取value都能取到，
    // 但是，value值里的属性上一条查询语句获取不到，除非强转成Map类型
    // 这里的爆红不影响项目启动，只是我们安装的箭头插件提示
    @MapKey("name")
    Map<String, Student> selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}