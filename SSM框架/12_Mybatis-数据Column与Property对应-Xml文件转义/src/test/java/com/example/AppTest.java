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

    // TODO 很明显出现的一个问题：查询出来的数据存在，但是打印出来的数据却是null
    @Test
    public void testApp() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TblEmployeeMapper mapper = sqlSession.getMapper(TblEmployeeMapper.class);

        TblEmployee tblEmployee = new TblEmployee();
        tblEmployee.setEmployeeName("Lui Yu Ling");

        List<TblEmployee> tblEmployees = mapper.queryAllByLimit(tblEmployee, 0, 10);
        tblEmployees.forEach(System.out::println);

        sqlSession.close();
    }
}
