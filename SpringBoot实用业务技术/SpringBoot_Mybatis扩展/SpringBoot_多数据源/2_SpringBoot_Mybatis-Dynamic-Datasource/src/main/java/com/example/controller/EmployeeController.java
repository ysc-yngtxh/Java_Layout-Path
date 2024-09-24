package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (Employee)表控制层
 * @author 游家纨绔
 * @since 2023-09-02 14:47:54
 */
@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @RequestMapping("/selectAll")
    public List<Employee> selectAll(){
        return employeeService.selectAll();
    }
}