package com.example;

import com.example.pojo.Student;
import com.example.utils.MyBatisUtils;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMyBatis {

    // 测试方法。查询操作
    @Test
    public void testSelectList() throws IOException {
        // 访问mybatis读取student数据
        // 1、定义mybatis主配置文件的名称，从类路径的根开始(target/class)
        String config= "mybatis.xml";
        // 2、读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream(config);
        // 3、创建了SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 4、创建SqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        // 5、获取SqlSession对象，从SqlSessionFactory中获取SqlSession。就类似于获取JDBC中的Connection
        //    在使用JDBC的事务管理器的时候，它会把自动提交先关闭一下。所以默认方式获取的SqlSession是非自动提交事务。
        //    如果希望能够自动提交事务，可在方法中传入参数：SqlSession sqlSession = factory.openSession(true);
        SqlSession sqlSession = factory.openSession();
        // 6、【重要】指定要执行得sql语句的标识。sql映射文件中的namespace+"."+标签的id值
        String sqlId = "com.example.mapper.StudentDao.selectStudents";
        // 7、【重要】执行sql语句，通过sqlId找到语句
        List<Student> studentList = sqlSession.selectList(sqlId);
        // 8、输出结果
        // studentList.forEach( stu -> System.out.println(stu));
        for (Student stu : studentList) {
            System.out.println("查询的学生 = " + stu);
        }
        // 查询操作不需要开启事务，所以就不需要提交事务
        // 9、关闭资源
        sqlSession.close();
    }

    // 测试方法。使用工具类获取 SqlSession, 查询操作
    @Test
    public void selectStudentsParam() {
        // 1、通过工具类获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        // 2、【重要】指定要执行得sql语句的标识。sql映射文件中的 namespace+"."+标签的id值
        String sqlId = "com.example.mapper.StudentDao.selectStudentsParam";
        // 3、构造参数
        Student student = new Student();
        student.setAge(10);
        student.setName("终是%");
        // 4、执行SQL语句，使用SqlSession类的方法，并通过sqlId找到语句
        List<Student> studentList = sqlSession.selectList(sqlId, student);
        // 5、输出结果
        // studentList.forEach( stu -> System.out.println(sqlId));
        for (Student stu : studentList) {
            System.out.println(stu);
        }

        // 查询操作不需要开启事务，所以就不需要提交事务

        // 6、关闭资源
        sqlSession.close();
    }

    // 测试方法。插入操作
    @Test
    public void testInsert() throws IOException {
        // 1、通过工具类获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        // 2、【重要】指定要执行得sql语句的标识。sql映射文件中的 namespace + "." + 标签的id值
        String sqlId = "com.example.mapper.StudentDao.insertStudent";
        // 3、构造参数
        Student student = new Student();
        student.setName("曹操");
        student.setEmail("caocao@163.com");
        student.setAge(23);
        // 4、【重要】执行sql语句，通过sqlId找到语句
        int nums = sqlSession.insert(sqlId, student);

        // 由于获取的SqlSession不是自动提交事务的，所以在insert,update,delete后要手工提交事务
        sqlSession.commit();

        // 5、输出结果
        System.out.println("执行insert的结果：" + nums + "。返回增加数据的Id--" + student.getId());
        // 6、关闭资源
        sqlSession.close();
    }
}
