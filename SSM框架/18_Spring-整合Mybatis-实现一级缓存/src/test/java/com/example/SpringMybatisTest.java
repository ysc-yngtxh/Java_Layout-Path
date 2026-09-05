package com.example;

import com.example.mapper.StudentMapper;
import com.example.pojo.Student;
import com.example.service.StudentService;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

// @ExtendWith(SpringExtension.class)：这是一个 JUnit 5 的扩展机制注解。
//     作用：使得单元测试可以在 Spring框架 提供的环境下执行属性注入。
//          如：在可以在单元测试中使用 @Autowired 注解，以便管理注入 Spring容器 中的 Bean
// 在 JUnit 4 时代，这个功能由 @RunWith(SpringRunner.class) 实现。升级到 JUnit 5 后，@RunWith 被弃用，统一改用 @ExtendWith。
@ExtendWith(SpringExtension.class)
// @ContextConfiguration：告诉 Spring 去哪里读取配置，来构建应用程序上下文（ApplicationContext）
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
// @SpringJUnitConfig：是 @ExtendWith(SpringExtension.class) 和 @ContextConfiguration 的组合注解。
// @SpringJUnitConfig(locations = {"classpath:applicationContext.xml"})
public class SpringMybatisTest {

	@Autowired
	private StudentService studentService;

	// 遍历Spring容器中的所有的Bean
	@Test
	public void test01() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		String[] names = ac.getBeanDefinitionNames();
		for (String na : names) {
			System.out.println("容器中对象的名称 " + na + " | " + ac.getBean(na));
		}
	}

	// 执行新增数据操作
	@Test
	public void test02() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentMapper mapper = ac.getBean("studentMapper", StudentMapper.class);
		Student student = new Student();
		student.setId(1020);
		student.setName("我like小曹");
		student.setEmail("lijingjing@163.com");
		student.setAge(2020919);
		int nums = mapper.insertStudent(student);
		// Spring 和 Mybatis 整合在一起使用，事务是自动提交的。无需执行 SqlSession.commit()
		System.out.println("nums = " + nums);
	}


	// TODO Spring整合MyBatis，因为SqlSession是非线程安全的，所以Mybatis提供了一个 SqlSessionTemplate 作为SqlSession的实现类。
	//      SqlSessionTemplate对象，内部是通过TheadLocal包装实现的线程安全，并将其作为实现 Mapper 代理的主要对象。
	//      SqlSessionTemplate 对象在每次执行Mapper方法时，会获取当前线程上下文中的 SqlSession 对象，然后调用相应的Mapper方法。
	//      这样做的好处是可以确保每个线程都使用独立的SqlSession对象，从而保证线程安全性。

	// 通过同一个sqlSession，获取Mybatis的一级缓存
	@Test
	public void test03() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 获取 SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = ac.getBean("sqlSessionFactory", SqlSessionFactory.class);

		// try-with-resources语法糖，并开启一个新的 SqlSession
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			// 获取 StudentService 实例，并注入当前的 SqlSession
			StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);

			List<Student> students = mapper.selectStudents();
			students.forEach(System.out::println);

			System.err.println("=================================================================");

			List<Student> students2 = mapper.selectStudents();
			students2.forEach(System.out::println);
		}
	}

	// 1、Spring整合的MyBatis，是不需要开发者手动去关闭SqlSession。（正常我们使用SqlSession结束后需要手动关闭连接）
	//    如果 SqlSession 在使用完毕后不被关闭，那么它将持续占用数据库连接和其他相关资源，这可能会导致资源耗尽，特别是当并发请求较多时。
	// 2、Spring整合的MyBatis，Mapper代理对象和Service都已交给Spring容器管理，因此我们两个查询方法所获取Mapper代理对象将会是同一个。
	//    但是每执行完一个Mapper方法，都会通过在动态代理切面编程的Finally语句中关闭掉SqlSession，因此不需要开发者手动去关闭SqlSession。
	// 3、Mybatis把SqlSession都被关闭掉了，一级缓存自然就没有任何数据，因此第二个查询就只能再重新查询数据库。
	//    但是可以通过添加事务注解@Transactional，来保证两个查询方法在彻底执行完之前不关闭sqlSession，这样第二个查询就能获取一级缓存数据。
	@Test
	@Transactional
	public void test04() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		StudentService studentService = ac.getBean("studentService", StudentService.class);

		List<Student> students = studentService.selectStudents();
		students.forEach(System.out::println);

		System.err.println("=================================================================");

		List<Student> students2 = studentService.selectStudents();
		students2.forEach(System.out::println);
	}

	@Test
	@Transactional
	public void test5() {
		List<Student> students = studentService.selectStudents();
		students.forEach(System.out::println);

		System.err.println("=================================================================");

		List<Student> students2 = studentService.selectStudents();
		students2.forEach(System.out::println);
	}

}
