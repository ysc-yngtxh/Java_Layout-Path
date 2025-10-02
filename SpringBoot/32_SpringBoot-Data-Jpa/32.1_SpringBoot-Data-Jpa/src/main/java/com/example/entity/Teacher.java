package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 游家纨绔
 * @dateTime 2025-08-23 15:30
 * @apiNote TODO 教师表
 */
@Setter
@Getter
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
@Table(name = "db_teacher", schema = "springboot", catalog = "springboot",
		uniqueConstraints = {
				@UniqueConstraint(name = "idx_teacher_code", columnNames = {"teacher_code"})
		},
		indexes = {
				// 该注解会指示JPA在自动建表时创建三个索引。
				//    name：索引的名称（可选，不指定索引名，Jpa将自动生成名称）。
				//    columnList：要创建索引的列名，多个列用逗号分隔。可以指定排序方式（ASC, DESC）。
				//    unique：是否为唯一索引，默认为 false
				@Index(name = "idx_name", columnList = "name"),
				@Index(name = "idx_department_id", columnList = "department_id DESC"),
				@Index(columnList = "name, phone", unique = false)
		}
)
public class Teacher implements Serializable {
	private static final long serialVersionUID = 4342452282196303406L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "teacher_code")
	private String teacherCode;
	private String name;
	private Integer gender;
	private Integer age;
	private String email;
	private String phone;
	@Column(name = "department_id", insertable = false, updatable = false)
	private Integer departmentId;
	private String title;
	private BigDecimal salary;
	@Column(name = "hire_date")
	private LocalDate hireDate;
	@Column(name = "is_full_time")
	private Boolean isFullTime;
	private String description;
	@Column(name = "create_time")
	private LocalDateTime createTime;
	@Column(name = "update_time")
	private LocalDateTime updateTime;


	// TODO 在进行一对一关联映射时，必须在关系的拥有方（主控方）使用 @JoinColumn 注解来指定外键列。
	//   拥有方（主控方）：  包含外键列的一方，需要负责维护关联关系。
	//   被拥有方（非主控方：不包含外键列的一方，不负责维护关联关系，但需要使用 mappedBy 属性来指示关系的维护端。
	@OneToOne(
			cascade = {CascadeType.ALL},
			// cascade 属性用于级联操作
			//     CascadeType.ALL：表示对父实体执行的所有操作（如保存、更新、删除等）都会级联到子实体。
			//     CascadeType.PERSIST：表示当保存父实体时，也会保存其关联的子实体。
			//     CascadeType.MERGE：表示当更新父实体时，也会更新其关联的子实体。
			//     CascadeType.REMOVE：表示当删除父实体时，也会删除其关联的子实体。
			//     CascadeType.REFRESH：表示当刷新父实体时，也会刷新其关联的子实体。
			//     CascadeType.DETACH：表示当分离父实体时，也会分离其关联的子实体。
			fetch = FetchType.EAGER,
			// fetch 属性用于指定关联实体的加载策略（默认 LAZY）
			//     EAGER（急加载）：表示在加载父实体时，立即加载所有关联的子实体。这可能会导致性能问题，尤其是当关联实体数量较多时。
			//     LAZY（懒加载：表示在访问关联实体时才加载数据，这有助于提高性能，尤其是在不需要立即访问关联实体的情况下。
	        orphanRemoval = true // 当从集合中移除子实体时，自动删除数据库中的对应记录
	)
	@JoinColumn(
			name = "department_id",      // 当前实体类中的外键字段
			referencedColumnName = "id", // 关联实体类中的主键字段
			foreignKey = @ForeignKey(name = "fk_department_id"), // 定义外键约束的详细信息
			nullable = false,    // 该外键列不能为空
			unique = true,       // 该外键列必须唯一
			insertable = false,  // 该外键列在插入操作中不会被包含
			updatable = false,   // 该外键列在更新操作中不会被包含
			columnDefinition = "INT DEFAULT 0", // 定义该外键列的SQL片段
			table = "db_teacher" // 该外键列所在的表名
	)
	private Department department;


	// TODO 在进行一对多的关联映射时
	//      @OneToMany 通常是关系的「被控方」（Inverse Side），而 mappedBy 属性只能用在「被控方」
	//      @ManyToOne 永远是关系的「主控方」（Owning Side）
	//      原因：一对多关系中，设计表结构时候就应该让多的一方持有外键，负责维护关联关系。
	//           那为什么要设计多的一方持有外键？
	//           假如让一的一方持有外键，那么一个教师只能关联到一个学生，无法实现一个教师有多个学生的需求。
	//      综上所述：
	//           多对一关系中，必须在多的一方持有外建，并使用 @JoinColumn 注解来指定外键列。
	@OneToMany(
			mappedBy = "teacher",         // mappedBy 属性用于指定关系的维护端，即在哪个实体中定义了关联关系。
			targetEntity = Student.class, // targetEntity 属性用于指定关联的实体类。
			cascade = {CascadeType.ALL},
			// cascade 属性用于级联操作
			//     CascadeType.ALL：表示对父实体执行的所有操作（如保存、更新、删除等）都会级联到子实体。
			//     CascadeType.PERSIST：表示当保存父实体时，也会保存其关联的子实体。
			//     CascadeType.MERGE：表示当更新父实体时，也会更新其关联的子实体。
			//     CascadeType.REMOVE：表示当删除父实体时，也会删除其关联的子实体。
			//     CascadeType.REFRESH：表示当刷新父实体时，也会刷新其关联的子实体。
			//     CascadeType.DETACH：表示当分离父实体时，也会分离其关联的子实体。
			fetch = FetchType.EAGER,
			// fetch 属性用于指定关联实体的加载策略（默认 LAZY）
			//     EAGER（急加载）：表示在加载父实体时，立即加载所有关联的子实体。这可能会导致性能问题，尤其是当关联实体数量较多时。
			//     LAZY（懒加载：表示在访问关联实体时才加载数据，这有助于提高性能，尤其是在不需要立即访问关联实体的情况下。
	        orphanRemoval = true // 当从集合中移除子实体时，自动删除数据库中的对应记录
	)
	private List<Student> students;






	// TODO 当实体类中存在双向关联关系的字段时，不能使用 @Data、@ToString 注解，
	//      否则会导致循环引用【即字段属性的打印会一直嵌套下去】，进而引发栈溢出异常。
	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", teacherCode='" + teacherCode + '\'' +
				", name='" + name + '\'' +
				", gender=" + gender +
				", age=" + age +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", departmentId=" + departmentId +
				", title='" + title + '\'' +
				", salary=" + salary +
				", hireDate=" + hireDate +
				", isFullTime=" + isFullTime +
				", description='" + description + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", department=Department{" +
				"id=" + department.getId() +
				", name='" + department.getName() + '\'' +
				", description='" + department.getDescription() + '\'' +
				", delFlag=" + department.getDelFlag() +
				", createTime=" + department.getCreateTime() +
				", updateTime=" + department.getUpdateTime() +
				"}" +
				", students=" +
				(students == null ? "null" : students.stream()
				                                     .map(s -> "Student{id=" + s.getId() + ", name=" + s.getName() + ", teacher_id=" + s.getTeacherId() + ", email=" + s.getEmail() + ", age=" + s.getAge() + "}")
				                                     .collect(Collectors.toList())) +
				"}";
	}
}
