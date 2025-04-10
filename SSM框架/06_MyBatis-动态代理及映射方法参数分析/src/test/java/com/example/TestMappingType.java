package com.example;

import com.example.mapper.MappingTypeDao;
import com.example.pojo.Student;
import com.example.utils.MyBatisUtils;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestMappingType {

    // 一个简单类型参数
    @Test
    public void testSelectStudents() {

        /**
         * 使用mybatis的动态及代理机制，使用SqlSession.getMapper(dao接口)
         * getMapper能获取dao接口对于的实现类对象
         */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        MappingTypeDao dao = sqlSession.getMapper(MappingTypeDao.class);
        /**
         * 可以发现这里打印出的 mapper=com.sun.proxy.$Proxy6：即为jdk的动态代理
         * System.out.println("mapper=" + mapper.getClass().getName());
         */
        // 调用dao的方法，执行数据库的操作
        Student Student = dao.selectStudentsType(1);
        System.out.println("Student = " + Student);
        sqlSession.close();
    }

    // 多个参数，使用Java对象作为接口中方法的参数
    @Test
    public void testSelectMultiStudent() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        MappingTypeDao dao = sqlSession.getMapper(MappingTypeDao.class);

        Student Student = new Student();
        Student.setName("张飞");
        Student.setAge(23);
        List<Student> Students = dao.selectMultiObject(Student);

        for (Student stu : Students) {
            System.out.println("学生" + stu);
        }
        sqlSession.close();
    }
}
