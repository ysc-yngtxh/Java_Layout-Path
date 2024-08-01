package com.example.mapper;

import com.example.entity.TblEmployee;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (TblEmployee)表数据库访问层
 *
 * @author makejava
 * @since 2024-08-01 00:05:26
 */
public interface TblEmployeeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param employeeId 主键
     * @return 实例对象
     */
    TblEmployee queryById(Integer employeeId);

    /**
     * 查询指定行数据
     *
     * @param tblEmployee 查询条件
     * @return 对象列表
     */
    List<TblEmployee> queryAllByLimit(@Param("tblEmployee") TblEmployee tblEmployee, @Param("offset") Integer offset, @Param("size") Integer size);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tblEmployee
     * @return
     */
    Long count(TblEmployee tblEmployee);
}

