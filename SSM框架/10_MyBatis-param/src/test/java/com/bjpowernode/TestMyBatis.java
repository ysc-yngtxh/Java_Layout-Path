package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {

    @Test//一个简单类型参数
    public void testSelectStudents(){

        /**
         * 使用mybatis的动态及代理机制，使用SqlSession.getMapper(dao接口)
         * getMapper能获取dao接口对于的实现类对象
         */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        /**
         * com.sun.proxy.$Proxy2：jdk的动态代理
         * System.out.println("dao="+dao.getClass().getName());
         */
        //调用dao的方法，执行数据库的操作
        Student student = dao.selectStudents(1);
        System.out.println("student="+student);
        sqlSession.close();
    }

    @Test//多个参数，使用@Param命名参数
    public void testSelectMultiParam(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> students = dao.selectMultiParam("李四",20);

        for (Student stu:students
             ) {
            System.out.println("学生"+stu);
        }
        sqlSession.close();
    }

    @Test//多个参数，使用Java对象作为接口中方法的参数
    public void testSelectMultiStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("张飞");
        student.setAge(23);
        List<Student> students = dao.selectMultiObject(student);

        for (Student stu:students
        ) {
            System.out.println("学生"+stu);
        }
        sqlSession.close();
    }

}
