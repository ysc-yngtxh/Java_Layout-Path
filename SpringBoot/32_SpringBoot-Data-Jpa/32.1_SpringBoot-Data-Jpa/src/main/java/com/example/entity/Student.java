package com.example.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 @author 游家纨绔
 @dateTime 2025-08-22 08:00
 @apiNote TODO 学生表
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
@Table(name = "db_student")
public class Student implements Serializable {
	private static final long serialVersionUID = -6186149606519563816L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(name = "teacher_id", insertable = false, updatable = false)
	private Integer teacherId;
	private String email;
	private Integer age;


	// TODO 在进行多对一的关联映射时
	//      @ManyToOne 永远是关系的「主控方」（Owning Side）
	//      @OneToMany 通常是关系的「被控方」（Inverse Side），而 mappedBy 属性只能用在「被控方」
	//      原因：多对一关系中，设计表结构时候就应该让多的一方持有外键，负责维护关联关系。
	//           那为什么要设计多的一方持有外键？
	//           假如让一的一方持有外键，那么一个教师只能关联到一个学生，无法实现一个教师有多个学生的需求。
	//      综上所述：
	//           多对一关系中，必须在多的一方持有外建，并使用 @JoinColumn 注解来指定外键列。
	@ManyToOne(
			fetch = FetchType.EAGER,      // fetch 属性用于指定关联的加载策略，默认为 FetchType.EAGER，表示关联在查询时立即加载。如果设置为 FetchType.LAZY，则表示关联在访问时才加载。
			cascade = {CascadeType.ALL},  // cascade 属性用于指定级联操作，默认为空，表示不进行级联操作。
			optional = false              // optional 属性用于指定关联是否可选，默认为 true，表示关联是可选的。如果设置为 false，则表示关联是必需的，不能为 null。
	)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;


	// TODO 在进行多对多的关联映射时，必须在关系的拥有方（主控方）使用 @JoinTable 注解来指定连接表。
	@ManyToMany
	@JoinTable(name = "db_student_course",
			joinColumns = @JoinColumn(name = "student_id", unique = true),
			inverseJoinColumns = @JoinColumn(name = "course_id", unique = true))
	private List<Course> courses;
}
