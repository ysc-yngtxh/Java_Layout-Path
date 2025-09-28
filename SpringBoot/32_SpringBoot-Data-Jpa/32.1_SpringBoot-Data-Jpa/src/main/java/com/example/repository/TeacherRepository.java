package com.example.repository;

import com.example.entity.Student;
import com.example.entity.Teacher;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	// TODO: Sql中传参两种方式在 JPQL 和 Native SQL 中的区别
	//    1. JPQL 中，使用 ?1, ?2... 这种方式传参时，参数的位置必须和方法中的参数位置一一对应
	//    2. JPQL 中，使用 :name 这种方式传参时，参数的位置可以和方法中的参数位置不对应，但参数名称必须和 @Param 注解中的名称一致
	//	  3. Native SQL 中，使用 ?1, ?2... 这种方式传参时，参数的位置必须和方法中的参数位置一一对应
	//    4. Native SQL 中，使用 :name 这种方式传参时，参数的位置可以和方法中的参数位置不对应，但参数名称必须和 @Param 注解中的名称一致


	// TODO JPQL 形式的 sql 语句，from 后面是以类名呈现的。
    // 示例 1、?1 代表是的是方法中的第一个参数, ?2 代表是的是方法中的第二个参数, 以此类推......
    @Query("select s from Teacher s where s.name = ?1")
    List<Teacher> findTeacher1(String name);
	// 示例 2、:name 是通过 @Param 注解去确定的
	@Query("select t from Teacher t where t.age = :age")
	List<Teacher> getTeachers(@Param("age") Integer age);
 
    // TODO 原生的 sql 语句，需要使用 nativeQuery = true 属性来指定使用原生 sql
    // 示例 1、?1 代表是的是方法中的第一个参数, ?2 代表是的是方法中的第二个参数, 以此类推......
    @Query(nativeQuery = true, value = "select c.* from db_teacher c where c.name = ?1")
    List<Teacher> findTeacher2(String name);
    // 示例 2、:name 是通过 @Param 注解去确定的。这里传参的顺序和方法中的参数顺序不一致，会导致查询报错。
    @Query(nativeQuery = true, value = "select c.* from db_teacher c where c.gender = :gender and c.age = :age")
    List<Teacher> getTeachers2(@Param("age") Integer age, @Param("gender") Integer gender);



	// TODO 在 JPQL 中使用 SpEL 表达式（原生Sql 不具备 SpEL能力）
	// 示例 1、直接使用方法参数
	@Query("select t from Teacher t where t.email = :email AND t.name = :#{#name}")
	List<Teacher> findByEmailAndStatus(@Param("email") String email, String name);
	// 示例 2、更复杂的表达式，访问参数的属性
	@Query(value = "select t from Teacher t where t.age = :#{#stu.age}")
	List<Teacher> getTeachersFromStudentInfo(@Param("stu") Student student);
	// 示例 3、使用条件运算符 (三元运算符)。如果 minAge 为 null，则默认使用 18
	@Query("SELECT t FROM Teacher t WHERE t.age >= :#{#minAge != null ? #minAge : 18}")
	List<Teacher> findUsersByMinAge(@Param("minAge") Integer minAge);



	@Query("select t from Teacher t where t.age = :age")
	List<Teacher> getTeachersAndSort(@Param("age") Integer age, Sort sort);
	// 使用 With子句 的形式，进行查询
	@Query(nativeQuery = true, value = "with t as (select * from db_teacher where age>:age) select * from t")
	List<Teacher> getTeachersWithCTE(@Param("age") Integer age);

}
