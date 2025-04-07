package com.example.mapper;

import com.example.entity.SSMEmployee;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (SSMEmployee)表数据库访问层
 *
 * @author makejava
 * @since 2024-08-01 00:05:26
 */
public interface EmployeeMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param employeeId 主键
     * @return 实例对象
     */
    SSMEmployee queryById(Integer employeeId);

    /**
     * 查询指定行数据
     *
     * @param SSMEmployee 查询条件
     * @return 对象列表
     */
    List<SSMEmployee> queryAllByLimit(@Param("SSMEmployee") SSMEmployee SSMEmployee, @Param("offset") Integer offset, @Param("size") Integer size);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param SSMEmployee
     * @return
     */
    List<SSMEmployee> findById(@Param("SSMEmployee") SSMEmployee SSMEmployee);
}
