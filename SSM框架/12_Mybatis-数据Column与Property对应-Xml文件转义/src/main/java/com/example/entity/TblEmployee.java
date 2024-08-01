package com.example.entity;

import java.io.Serializable;

/**
 * (TblEmployee)实体类
 *
 * @author makejava
 * @since 2024-08-01 00:05:26
 */
public class TblEmployee implements Serializable {
    private static final long serialVersionUID = 364552195684892347L;

    private Integer employeeId;

    private String employeeName;

    private Integer employeeDepartmentId;

    private Integer employeeGradeId;

    private Integer employeeSalary;


    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeDepartmentId() {
        return employeeDepartmentId;
    }

    public void setEmployeeDepartmentId(Integer employeeDepartmentId) {
        this.employeeDepartmentId = employeeDepartmentId;
    }

    public Integer getEmployeeGradeId() {
        return employeeGradeId;
    }

    public void setEmployeeGradeId(Integer employeeGradeId) {
        this.employeeGradeId = employeeGradeId;
    }

    public Integer getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Integer employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

}

