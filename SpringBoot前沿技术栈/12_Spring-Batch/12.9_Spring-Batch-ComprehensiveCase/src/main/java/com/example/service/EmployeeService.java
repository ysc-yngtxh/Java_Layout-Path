package com.example.service;

import com.example.entity.Employee;

import java.io.IOException;

/**
 * (Employee)表服务接口
 *
 * @author makejava
 * @since 2023-06-27 23:55:24
 */
public interface EmployeeService {

    /**
     * 保存
     */
    void save(Employee employee);

    /**
     * 初始化数据：生成50w数据
     */
    void dataInit() throws IOException;

    /**
     * 清空数据
     */
    void truncateAll();

    /**
     * 清空employee_temp数据
     */
    void truncateTemp();
}
