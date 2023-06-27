package com.example.controller;

import com.example.entity.Employee;
import com.example.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2023-06-27 23:55:14
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Employee> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.employeeService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param employee 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Employee> add(Employee employee) {
        return ResponseEntity.ok(this.employeeService.insert(employee));
    }

    /**
     * 编辑数据
     *
     * @param employee 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Employee> edit(Employee employee) {
        return ResponseEntity.ok(this.employeeService.update(employee));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.employeeService.deleteById(id));
    }

}

