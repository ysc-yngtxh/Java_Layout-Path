package com.example;

import com.example.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMyBatis {

    // 测试方法，测试功能
    @Test
    public void testInsert() throws IOException {
        // 1、定义mybatis主配置文件的名称，从类路径的根开始(target/class)
        String config= "mybatis.xml";
        // 2、读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);
        // 3、创建了SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 4、创建SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        // 5、获取SqlSession对象，从SqlSessionFactory中获取SqlSession.
        //    在使用JDBC的事务管理器的时候，它会把自动提交先关闭一下。所以默认方式获取的SqlSession是非自动提交事务。
        //    如果希望能够自动提交事务，可在方法中传入参数：SqlSession sqlSession = factory.openSession(true);
        SqlSession sqlSession = factory.openSession();
        // 6、【重要】指定要执行得sql语句的标识。sql映射文件中的 namespace + "." + 标签的id值
        String sqlId = "com.example.dao.StudentDao.insertStudent";
        // 7、【重要】执行sql语句，通过sqlId找到语句
        Student student = new Student();
        student.setName("曹操");
        student.setEmail("caocao@163.com");
        student.setAge(23);
        int nums = sqlSession.insert(sqlId, student);

        // 由于获取的SqlSession不是自动提交事务的，所以在insert,update,delete后要手工提交事务
        sqlSession.commit();

        // 8、输出结果
        System.out.println("执行insert的结果：" + nums + "。返回增加数据的Id--" + student.getId());
        // 9、关闭资源
        sqlSession.close();
    }
}
