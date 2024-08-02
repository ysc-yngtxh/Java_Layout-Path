package com.example;

import com.example.entity.TblEmployee;
import com.example.mapper.TblEmployeeMapper;
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
        TblEmployeeMapper mapper = getSqlSessionFactory().getMapper(TblEmployeeMapper.class);

        TblEmployee tblEmployees = mapper.queryById(8);
        System.out.println(tblEmployees);

        getSqlSessionFactory().close();
    }

    // TODO 在 xml 文件中，未进行 column 与 property 的对应关系，返回值使用 resultType
    //      问题：当 column 与 property 不一致时，查询出来的数据存在，但是打印出来的数据却是null。
    //      解决方案：1、可以使用 resultMap 来进行映射
    //              2、使用 Mybatis-Plus 的 @TableField 注解
    @Test
    public void testApp2() throws IOException {
        TblEmployeeMapper mapper = getSqlSessionFactory().getMapper(TblEmployeeMapper.class);

        TblEmployee tblEmployee = new TblEmployee();
        tblEmployee.setEmployeeName("Lui Yu Ling");

        List<TblEmployee> tblEmployees = mapper.queryAllByLimit(tblEmployee, 0, 10);
        tblEmployees.forEach(System.out::println);

        getSqlSessionFactory().close();
    }



    @Test
    public void testApp3() throws IOException {
        TblEmployeeMapper mapper = getSqlSessionFactory().getMapper(TblEmployeeMapper.class);

        TblEmployee tblEmployee = new TblEmployee();
        tblEmployee.setEmployeeId(3);
        tblEmployee.setEmployeeGradeId(100);
        tblEmployee.setEmployeeSalary(700);

        List<TblEmployee> tblEmployees = mapper.findById(tblEmployee);
        tblEmployees.forEach(System.out::println);

        getSqlSessionFactory().close();
    }
}
