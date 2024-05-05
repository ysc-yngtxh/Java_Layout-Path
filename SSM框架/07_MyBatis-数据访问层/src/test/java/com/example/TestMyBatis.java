package com.example;

import com.example.dao.StudentDao;
import com.example.domain.Student;
import com.example.dao.StudentDaoImpl;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {

    @Test
    public void testSelectStudents(){
        // com.example.dao.StudentDao
        StudentDao dao = new StudentDaoImpl();
        /**
         * List<Student> student = dao.selectStudent();调用
         * 1、dao对象，类型是StudentDao，全限定名称是：com.example.dao.StudentDao
         *    全限定名称 和 namespace 是保持一样的
         * 2、方法名称为selectStudents，这个方法对应mapper文件中的 id值：selectStudents
         * 3、通过dao中方法的返回值可以确定MyBatis要调用的SqlSession的方法
         *    如果返回值是List，调用的是SqlSession.selectList()方法。
         *    如果返回值是int,或是非List的，看mapper文件中的标签<insert>,<update> 就会调用SqlSession的insert,update等方法
         *
         *    mybatis的动态代理：mybatis根据dao的方法调用，获取执行sql语句的信息。
         *    mybatis根据你的dao接口，创建出一个dao接口的实现类，并创建这个类的对象。
         *    完成SqlSession调用方法，访问数据库。
         */
        List<Student> studentList = dao.selectStudents();
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }

    @Test
    public void selectStudentsParam() {
        StudentDao dao = new StudentDaoImpl();
        List<Student> studentList = dao.selectStudentsParam(10, "终是%");
        for (Student stu : studentList) {
            System.out.println(stu);
        }
    }

    @Test
    public void testInsertStudent(){
        StudentDao dao = new StudentDaoImpl();
        Student student = new Student();
        student.setName("盾山");
        student.setEmail("dunshan@163.com");
        student.setAge(50);
        int nums = dao.insertStudent(student);
        System.out.println("添加对象的数量：" + nums);
        System.out.println("添加对象后返回的Id值：" + student.getId());
    }
}
