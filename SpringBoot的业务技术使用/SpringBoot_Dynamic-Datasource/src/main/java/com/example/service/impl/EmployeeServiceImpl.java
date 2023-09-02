package com.example.service.impl;

import com.example.entity.Employee;
import com.example.mapper.springdb.EmployeeMapper;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Employee)表服务实现类
 * @author 游家纨绔
 * @since 2023-09-02 14:47:54
 */
@Service("employeeService")
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;

    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }
}