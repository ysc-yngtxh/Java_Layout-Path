package com.example;

import com.example.domain.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @RunWith(SpringRunner.class)：主要作用是使得单元测试可以在Spring框架提供的环境下执行属性注入。
//                               如：在单元测试中使用 @Autowired 注解，注入Spring容器中管理的Bean
// 如果是 Junit 4.x 则需要加 @RunWith，是 Junit 5.x 就不需要加，因为内置了。
@RunWith(SpringRunner.class)
// @ContextConfiguration：指定了用于测试的应用程序上下文从哪个XML配置文件加载Spring的bean定义。
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MyApp {

    // 遍历Spring容器中的所有的Bean
    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] names = ac.getBeanDefinitionNames();
        for (String na : names) {
            System.out.println("容器中对象的名称 " + na + " | " + ac.getBean(na));
        }
    }

    // 执行新增数据操作
    @Test
    public void test02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentMapper mapper = (StudentMapper) ac.getBean("studentMapper");
        Student student = new Student();
        student.setId(1020);
        student.setName("我喜欢曹玉敏");
        student.setEmail("lijingjing@163.com");
        student.setAge(2020919);
        int nums = mapper.insertStudent(student);
        // Spring和Mybatis整合在一起使用，事务是自动提交的。无需执行SqlSession.commit()
        System.out.println("nums = " + nums);
    }


    // 通过同一个sqlSession，获取Mybatis的一级缓存
    @Test
    public void test03(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取 SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) ac.getBean("sqlSessionFactory");

        // 开启一个新的 SqlSession
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取 StudentService 实例，并注入当前的 SqlSession
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

            List<Student> students = mapper.selectStudents();
            List<Student> students2 = mapper.selectStudents();
            for (Student stu : students2) {
                System.out.println(stu);
            }
        }
    }


    // TODO Spring整合MyBatis时，会使用SqlSessionTemplate作为SqlSession的实现类，默认情况下会自动创建一个SqlSessionTemplate对象，
    //      并将其注入到Mapper代理对象中，这个SqlSessionTemplate对象会在每次执行Mapper方法时，
    //      自动获取当前线程上下文中的SqlSession对象，然后调用相应的Mapper方法。
    //      因此，SqlSession对象不是每次执行Mapper方法都会创建，而是在需要时从线程上下文中获取。
    //      这样做的好处是可以确保每个线程都使用独立的SqlSession对象，从而保证线程安全性。

    // 因为我们的Mapper代理对象和Service都已交给Spring容器管理，因此我们两个查询方法所获取Mapper代理对象是同一个。
    // 但是每执行完一个Mapper方法，都会关闭掉SqlSession。
    // 因为如果 SqlSession 在使用完毕后不被关闭，那么它将持续占用数据库连接和其他相关资源，这可能会导致资源耗尽，特别是当并发请求较多时。
    // 因此SqlSession都被关闭掉了，一级缓存自然就没有任何数据，因此第二个查询就只能再重新查询数据库。
    // 可以通过添加事务注解Transactional，来保证两个查询方法在彻底执行完之前不关闭sqlSession，这样第二个查询就能获取一级缓存数据
    @Test
    @Transactional
    public void test04(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService studentService = (StudentService) ac.getBean("studentService");
        List<Student> students = studentService.selectStudents();
        List<Student> students2 = studentService.selectStudents();
    }


    @Autowired
    private StudentService service;

    @Test
    @Transactional
    public void test5(){
        List<Student> students = service.selectStudents();
        List<Student> students2 = service.selectStudents();
    }
}
