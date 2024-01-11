package com.example;

import com.example.dao.StudentDao;
import com.example.domain.Student;
import com.example.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {

    @Test
    public void testSelectStudents(){

        /*
         * 使用mybatis的动态及代理机制，使用SqlSession.getMapper(dao接口)
         * getMapper能获取dao接口对于的实现类对象
         */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        /**
         * com.sun.proxy.$Proxy2：jdk的动态代理
         * System.out.println("dao=" + dao.getClass().getName());
         */
        // 调用dao的方法，执行数据库的操作
        List<Student> students = dao.selectStudents();
        for (Student stu : students) {
            System.out.println("学生=" + stu);
        }
    }

    @Test
    public void testInsertStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = new Student();
        student.setId(1007);
        student.setName("诸葛亮");
        student.setEmail("zhugeliang@163.com");
        student.setAge(35);
        int nums = dao.insertStudent(student);
        sqlSession.commit();
        System.out.println("添加对象的数量：" + nums);
    }
}
