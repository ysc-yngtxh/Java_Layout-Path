package com.example.service.impl;

import com.example.entity.Employee;
import com.example.mapper.business.EmployeeMapper;
import com.example.service.EmployeeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * (Employee)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-09-02 14:50:00
 */
@Service("employeeService")
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeMapper employeeMapper;

	public List<Employee> selectAll() {
		return employeeMapper.selectAll();
	}
}
