package com.example.mapper;

import com.example.entity.Employee;

import java.util.List;

/**
 * (Employee)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-27 23:55:16
 */
public interface EmployeeDao {

    /**
     * 添加
     */
    int save(Employee employee);

    /**
     * 添加临时表
     */
    int saveTemp(Employee employee);

    /**
     * 清空数据
     */
    void truncateAll();

    /**
     * 清空临时表数据
     */
    void truncateTemp();

    /**
     * 查询临时表数据
     */
    List<Employee> selectTemp();
}

