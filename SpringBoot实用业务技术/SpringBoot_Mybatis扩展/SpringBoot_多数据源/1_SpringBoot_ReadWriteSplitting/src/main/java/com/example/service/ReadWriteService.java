package com.example.service;

import com.example.annotation.Master;
import com.example.annotation.Slave;
import com.example.entity.Employee;
import com.example.entity.TbBrand;
import com.example.mapper.springdb.EmployeeMapper;
import com.example.mapper.yun6.TbBrandMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadWriteService {

    @Autowired
    private TbBrandMapper tbBrandMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Master
    public TbBrand getUserByBrand(String username) {
        return tbBrandMapper.selectByName(username);
    }

    @Slave
    public List<Employee> listAllBrand() {
        return employeeMapper.selectAll();
    }

    @Master
    public void saveBrand(TbBrand Brand) {
        tbBrandMapper.insert(Brand);
    }
}
