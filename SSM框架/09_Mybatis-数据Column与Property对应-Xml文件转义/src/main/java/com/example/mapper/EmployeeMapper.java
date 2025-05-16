package com.example.mapper;

import com.example.entity.Employee;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (Employee)表数据库访问层
 *
 * @author 游家纨绔
 * @since 2024-08-01 00:00:00
 */
public interface EmployeeMapper {

	/**
	 * 通过ID查询单条数据
	 *
	 * @param employeeId 主键
	 * @return 实例对象
	 */
	Employee queryById(Integer employeeId);

	/**
	 * 查询指定行数据
	 *
	 * @param Employee 查询条件
	 * @return 对象列表
	 */
	List<Employee> queryAllByLimit(@Param("Employee") Employee Employee, @Param("offset") Integer offset, @Param("size") Integer size);

	/**
	 * 通过实体作为筛选条件查询
	 *
	 * @param Employee
	 * @return
	 */
	List<Employee> findById(@Param("Employee") Employee Employee);
}
