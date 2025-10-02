package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2025-10-02 21:50
 * @apiNote TODO 课程表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @Entity(必选)：一个类要被持久化，就必须使用此注解。作用：用于标记该类为JPA实体类。
//      name 属性用于指定实体对应的数据库表名【可选】
@Entity
// @Table(可选)：用于指定实体类所映射的数据库表的信息。
//      如果不使用 @Table 注解且 @Entity也没有定义 name 属性，则默认会使用类名作为表名，且首字母小写
//      name 属性用于指定表名，schema 属性用于指定数据库模式，catalog 属性用于指定数据库目录。
//      uniqueConstraints 用于指定表中的唯一约束，可以有多个。indexes 用于指定表中的索引，可以有多个。
// uniqueConstraints 和 indexes 属性主要被Hibernate等 JPA提供商 的自动建表功能（如 hibernate.hbm2ddl.auto）所使用。
// 如果你手动管理数据库表结构，这些注解不会影响现有表，它们只是作为元数据存在。
@Table(name = "db_course")
public class Course implements Serializable {
	private static final long serialVersionUID = 3997089347115228106L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "course_code")
	private String courseCode;
	@Column(name = "course_name")
	private String courseName;
	private BigDecimal credit;
	private Integer hours;
	@Column(name = "teacher_id")
	private Integer teacherId;
	@Column(name = "max_students")
	private Integer maxStudents;
	private String description;
	private Object status;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;


	@ManyToMany(mappedBy = "courses")
	private List<Student> students;
}
