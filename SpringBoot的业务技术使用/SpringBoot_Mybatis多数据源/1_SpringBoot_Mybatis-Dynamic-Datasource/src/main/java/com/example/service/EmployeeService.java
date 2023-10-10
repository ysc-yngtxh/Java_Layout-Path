package com.example.service;

import com.example.entity.Employee;

import java.util.List;

/**
 * (Employee)表服务接口
 * @author 游家纨绔
 * @since 2023-09-02 14:47:54
 */
public interface EmployeeService {
    List<Employee> selectAll();
}