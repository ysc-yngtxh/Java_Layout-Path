package com.example.service;

import com.example.annotation.Master;
import com.example.annotation.Slave;
import com.example.entity.Brand;
import com.example.entity.Employee;
import com.example.mapper.business.EmployeeMapper;
import com.example.mapper.business2.BrandMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadWriteService {

	@Autowired
	private BrandMapper brandMapper;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Master
	public Brand getUserByBrand(String username) {
		return brandMapper.selectByName(username);
	}

	@Slave
	public List<Employee> listAllBrand() {
		return employeeMapper.selectAll();
	}

	@Master
	public void saveBrand(Brand Brand) {
		brandMapper.insert(Brand);
	}
}
