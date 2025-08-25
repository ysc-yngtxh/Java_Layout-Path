package com.example.repository;

import com.example.entity.Teacher;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
 
    // JPQL 形式的 sql 语句，from 后面是以类名呈现的。
    // ?1 代表是的是方法中的第一个参数
    @Query("select s from Teacher s where s.name =?1")
    List<Teacher> findClassRoom1(String name);
 
    // 原生的 sql 语句，需要使用 nativeQuery = true 属性来指定使用原生 sql
    // :name 是通过 @Param 注解去确定的
    @Query(nativeQuery = true, value = "select * from teacher c where c.name =:name")
    List<Teacher> findClassRoom2(@Param("name")String name);

	// 正常使用，只是多加了一个 sort 参数而已
	@Query("select t from Teacher t where t.subject = ?1")
	List<Teacher> getTeachers(String subject, Sort sort);
}
