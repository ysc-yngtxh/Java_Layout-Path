package com.example.mapper.business;

import com.example.entity.Employee;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * (Employee)表数据库访问层
 * @author 游家纨绔
 * @since 2023-09-02 14:47:54
 */
public interface EmployeeMapper {

    /** @Results用法
     * 当数据库字段名与实体类对应的属性名不一致时，可以使用@Results映射来将其对应起来。
     * column为数据库字段名，property为实体类属性名，jdbcType为数据库字段数据类型，id为是否为主键。
     */
    @Select("select id,name,age,(CASE sex WHEN 0 THEN '女' WHEN 1 THEN '男' END) AS sex from business.employee")
    @Results(id = "selectResult", value = {
            // 这种注解类型其实跟xml配置有异曲同工之妙，但是不符合企业开发规范，自己项目做着玩就行
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true), // id = true表示是否为主键
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER),
            @Result(column = "sex", property = "sex", jdbcType = JdbcType.TINYINT)
    })
    List<Employee> selectAll();
}
