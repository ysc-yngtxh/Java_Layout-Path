package com.example;

import com.example.entity.Department;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@SpringBootTest
class OneManyApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	// TODO 使用 Spring Data Jpa 时，不要使用 @Data（其中包含@ToString） 或者 @ToString 等注解，
	//      这是因为 Lombok 生成的 toString() 方法会包含所有属性，包括那些使用 LAZY 或 EAGER 加载的属性。
	//      而在执行 ORM 的方法时，就会引发 java.lang.StackOverflowError 异常。
	//      例如：Student 类中有一个 Teacher 属性，Teacher 类中有一个 students 属性，
	//           当我们打印 Student 实体时，会调用 Teacher 的 toString() 方法，
	//           而 Teacher 的 toString() 方法又会调用 students 的 toString() 方法，
	//           这样就形成了一个循环引用，触发异常。

	@Test
	// 问题：Student 类中标注了 @ManyToMany 注解的属性 courses，其默认是 LAZY 进行加载，
	//      所以在打印（执行手写 toString()方法）时，有调用的 courses 属性，按理说会对 courses属性 进行关联查询，
	//      但此时 session 已经关闭，所以会报错：could not initialize proxy
	// 解决方案：
	//      1、修改 @ManyToMany(fetch = FetchType.EAGER) 为急加载
	//      2、在方法上添加 @Transactional 注解，开启事务管理，这样 session 就不会关闭，直到事务结束
	//      3、使用 DTO（Data Transfer Object）模式，避免直接暴露实体类
	//         通过 DTO 类来传输数据，而不是直接使用实体类
	//         这样可以避免懒加载的问题，同时也能更好地控制数据的暴露
	//      4、使用 fetch join 进行查询，强制加载关联数据
	//         例如：SELECT s FROM Student s JOIN FETCH s.courses WHERE s.id = :id
	//      5、使用 Hibernate.initialize() 方法来初始化懒加载的属性
	//         例如：Hibernate.initialize(student.getCourses());
	@Transactional
	void studentFindAll() {
		studentRepository.findAll().forEach(System.out::println);
	}

	@Test
	void teacherFindAll() {
		teacherRepository.findAll().forEach(System.out::println);
	}

	// 使用Criteria API实现动态查询
	@Test
	public void testJspSpecification1() {
		Teacher teacher = Teacher.builder()
		                         .name("张三")
		                         .age(25)
		                         .salary(new BigDecimal("8000"))
		                         .department(Department.builder().name("计算机系").build())
		                         .students(Collections.singletonList(Student.builder().name("李四").build()))
		                         .email("li").createTime(LocalDateTime.now())
		                         .build();

        // Root<T>：        根对象，即要查询的实体类，可表示实体的属性和嵌套属性，用来指定要查询的字段。
        // CriteriaQuery：  最终的查询对象，用于构建查询条件，所有的查询条件汇总到这个对象，由JPA去执行。
        // CriteriaBuilder：查询构建器，用于创建查询条件，如cb.isNull(root.get(“deleteTime”))
		Specification<Teacher> teacherSpecification =
				(root, criteriaQuery, criteriaBuilder) -> {
					List<Predicate> predicates = new ArrayList<>();
					// 等值查询
					if (Objects.nonNull(teacher.getName())) {
						predicates.add(criteriaBuilder.equal(root.get("name"), teacher.getName()));
					}
					// isNull查询
					predicates.add(criteriaBuilder.isNull(root.get("updateTime")));
					// isNotNull查询
					predicates.add(criteriaBuilder.isNotNull(root.get("email")));
					// in查询
					predicates.add(root.get("id").in(1, 2, 3, 4, 5));
					// between查询
					predicates.add(criteriaBuilder.between(root.get("age"), 20, 30));
					// 大于查询
					predicates.add(criteriaBuilder.greaterThan(root.get("salary"), 5000.0));
					// 小于查询
					predicates.add(criteriaBuilder.lessThan(root.get("salary"), 20000.0));
					// 大于等于查询
					predicates.add(
							criteriaBuilder.greaterThanOrEqualTo(
									// as()方法用于类型转换
									root.get("createTime").as(LocalDateTime.class),
									LocalDateTime.parse("2020-11-20 14:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
							)
					);
					// 连接查询（左外连接）
					if (Objects.nonNull(teacher.getDepartment()) && Objects.nonNull(teacher.getDepartment().getName())) {
						predicates.add(
								criteriaBuilder.equal(
										root.join("department", JoinType.LEFT).get("name"),
										teacher.getDepartment().getName()
								)
						);
					}
					// 连接查询（内连接）
					if (!CollectionUtils.isEmpty(teacher.getStudents())) {
						predicates.add(
								criteriaBuilder.equal(
										root.join("students", JoinType.INNER).get("name"),
										teacher.getStudents().get(0).getName()
								)
						);
					}
					// 大小写忽略的模糊查询
					predicates.add(
							criteriaBuilder.like(
									criteriaBuilder.lower(root.get("name")),
									"%" + teacher.getName().toLowerCase() + "%"
							)
					);
					// 排序
					criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
					// distinct 去重
					criteriaQuery.distinct(true);
					// 组合条件
					return criteriaQuery.where(predicates.toArray(new Predicate[]{})).getRestriction();
				};

		// 执行查询：
		//     SELECT DISTINCT *
		//     FROM db_teacher t
		//     LEFT OUTER JOIN db_department d ON t.department_id = d.id
		//     INNER JOIN db_student s ON t.id = s.teacher_id
		//     WHERE t.name = ?
		//       AND t.update_time IS NULL
		//       AND t.email IS NOT NULL
		//       AND t.id IN(1 , 2 , 3 , 4 , 5)
		//       AND t.age BETWEEN 20 AND 30
		//       AND t.salary > 5000.0
		//       AND t.salary < 20000.0
		//       AND t.create_time >= ?
		//       AND d.name = ?
		//       AND s.name = ?
		//       AND lower(t.name) LIKE ?
		//     ORDER BY t.id DESC
		teacherRepository.findAll(teacherSpecification).forEach(System.out::println);
	}

	@Test
	public void testJspSpecification2() {

	}


	// 使用 Example 实现动态查询。
	// Example 适用于简单的动态查询，不能进行复杂的查询操作，如排序、分页、连接查询等
	@Test
	public void testExample1() {
		Teacher teacher = new Teacher();
		teacher.setName("张三");
		Example<Teacher> example = Example.of(teacher);
		List<Teacher> list = teacherRepository.findAll(example);
		System.out.println(list);
	}

	@Test
	public void testExample2() {
		Teacher teacher = new Teacher();
		teacher.setName("y");
		teacher.setEmail("sh");
		teacher.setTitle("admin");
		// 写法一
		ExampleMatcher matcher =
				ExampleMatcher.matching()
				              // 模糊查询匹配开头，即{name}%
				              .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.startsWith())
				              // 全部模糊查询，即%{email}%
		                      .withMatcher("email" ,ExampleMatcher.GenericPropertyMatchers.contains())
		                      // 忽略字段，即不管title是什么值都不加入查询条件
		                      .withIgnorePaths("title");
		// 写法二
		ExampleMatcher matcher2 =
				ExampleMatcher.matching()
				              .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::endsWith)
				              .withMatcher("email", ExampleMatcher.GenericPropertyMatcher::startsWith);

		Example<Teacher> example = Example.of(teacher, matcher);
		List<Teacher> list = teacherRepository.findAll(example);
		System.out.println(list);
	}
}
