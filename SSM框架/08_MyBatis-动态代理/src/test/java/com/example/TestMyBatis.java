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
         * 可以发现这里打印出的 dao=com.sun.proxy.$Proxy6：即为jdk的动态代理
         * System.out.println("dao=" + dao.getClass().getName());
         */
        // StudentDao类是接口，无法实例化，我们却能通过“接口对象dao”调用方法，很明显这里是jdk动态代理的对象执行数据库的操作
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
