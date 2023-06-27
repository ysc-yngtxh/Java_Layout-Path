package com.example.service.impl;

import com.example.entity.Employee;
import com.example.dao.EmployeeDao;
import com.example.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (Employee)表服务实现类
 *
 * @author makejava
 * @since 2023-06-27 23:55:26
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeDao employeeDao;

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Employee queryById(Integer id) {
        return this.employeeDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee insert(Employee employee) {
        this.employeeDao.insert(employee);
        return employee;
    }

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee update(Employee employee) {
        this.employeeDao.update(employee);
        return this.queryById(employee.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.employeeDao.deleteById(id) > 0;
    }
}
