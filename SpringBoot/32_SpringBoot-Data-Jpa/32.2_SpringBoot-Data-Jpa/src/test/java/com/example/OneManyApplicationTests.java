package com.example;

import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class OneManyApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	// 问题：Student 类中的属性 courses 使用了 @ManyToMany 注解，默认是 LAZY 进行加载，
	//      所以在打印（执行 toString()方法）时，就调用的 courses 属性，按理会去数据库查询数据，
	//      但此时 session 已经关闭，所以会报错：could not initialize proxy
	// 解决方案：
	// 1. 修改 @ManyToMany(fetch = FetchType.EAGER)
	// 2. 在方法上添加 @Transactional 注解，开启事务管理，这样 session 就不会关闭，直到事务结束
	// 3. 使用 DTO（Data Transfer Object）模式，避免直接暴露实体类
	//    通过 DTO 类来传输数据，而不是直接使用实体类
	//    这样可以避免懒加载的问题，同时也能更好地控制数据的暴露
	// 4. 使用 fetch join 进行查询，强制加载关联数据
	//    例如：SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id = :id
	// 5. 使用 Hibernate.initialize() 方法来初始化懒加载的属性
	//    例如：Hibernate.initialize(student.getCourses());
	// 总结：
	// - 对于简单的测试，可以使用 @Transactional 注解
	// - 对于复杂的业务逻辑，建议使用 DTO 模式或 fetch join
	// - 避免在实体类中使用 @Data 或 @ToString 注解，以防止循环引用和栈溢出异常
	// - 理解 JPA 的加载策略（EAGER 和 LAZY）以及它们的适用场景
	// - 在设计数据库和实体类时，考虑到性能和数据一致性
	@Transactional
	void studentFindAll() {
		studentRepository.findAll().forEach(System.out::println);
	}

	@Test
	void teacherFindAll() {
		teacherRepository.findAll().forEach(System.out::println);
	}

}
