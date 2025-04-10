package com.example;

import com.example.entity.Employee;
import com.example.mapper.EmployeeMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class AppTest {

    public SqlSession getSqlSessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    // TODO 在 xml 文件中，column 与 property 的对应关系，使用 resultMap 来进行映射
    @Test
    public void testApp1() throws IOException {
        EmployeeMapper mapper = getSqlSessionFactory().getMapper(EmployeeMapper.class);

        Employee employees = mapper.queryById(8);
        System.out.println(employees);

        getSqlSessionFactory().close();
    }

    // TODO 在 xml 文件中，未进行 column 与 property 的对应关系，返回值使用 resultType
    //      问题：当 column 与 property 不一致时，查询出来的数据存在，但是打印出来的数据却是null。
    //      解决方案：1、可以使用 resultMap 来进行映射
    //              2、使用 Mybatis-Plus 的 @TableField 注解
    @Test
    public void testApp2() throws IOException {
        EmployeeMapper mapper = getSqlSessionFactory().getMapper(EmployeeMapper.class);

        Employee Employee = new Employee();
        Employee.setEmployeeName("Hu Jiehong");

        List<Employee> Employees = mapper.queryAllByLimit(Employee, 0, 10);
        Employees.forEach(System.out::println);

        getSqlSessionFactory().close();
    }

    // TODO 对于在 xml 文件中，针对 Sql 组装语句的转义问题
    @Test
    public void testApp3() throws IOException {
        EmployeeMapper mapper = getSqlSessionFactory().getMapper(EmployeeMapper.class);

        Employee Employee = new Employee();
        Employee.setEmployeeId(3);
        Employee.setEmployeeGradeId(100);
        Employee.setEmployeeSalary(700);

        List<Employee> Employees = mapper.findById(Employee);
        Employees.forEach(System.out::println);

        getSqlSessionFactory().close();
    }
}
