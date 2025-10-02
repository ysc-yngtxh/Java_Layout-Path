package com.example.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 游家纨绔
 * @dateTime 2025-10-02 13:30
 * @apiNote TODO 部门表
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
@Table(name = "db_department")
public class Department implements Serializable {
	private static final long serialVersionUID = -6961576955358897164L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	@Column(name = "del_flag")
	private Boolean delFlag;
	@Column(name = "create_time")
	private LocalDateTime createTime;
	@Column(name = "update_time")
	private LocalDateTime updateTime;


	// TODO 在进行关联映射时，必须在关系的拥有方（主控方）使用 @JoinColumn 注解来指定外键列。
	//   拥有方（主控方）：  包含外键列的一方，需要负责维护关联关系。
	//   被拥有方（非主控方：不包含外键列的一方，不负责维护关联关系，但需要使用 mappedBy 属性来指示关系的维护端。

	@OneToOne(
			mappedBy = "department",      // mappedBy 属性用于指定关系的维护端，即在哪个实体中定义了关联关系。
			targetEntity = Teacher.class, // targetEntity 属性用于指定关联的实体类。
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
	private Teacher teacher;







	// TODO 当实体类中存在双向关联关系的字段时，不能使用 @Data、@ToString 注解，
	//      否则会导致循环引用【即字段属性的打印会一直嵌套下去】，进而引发栈溢出异常。
	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", delFlag=" + delFlag +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", teacher=Teacher{" +
				"id=" + teacher.getId() +
				", teacherCode='" + teacher.getTeacherCode() + '\'' +
				", name='" + teacher.getName() + '\'' +
				", gender=" + teacher.getGender() +
				", age=" + teacher.getAge() +
				", email='" + teacher.getEmail() + '\'' +
				", phone='" + teacher.getPhone() + '\'' +
				", departmentId=" + teacher.getDepartmentId() +
				", title='" + teacher.getTitle() + '\'' +
				", salary=" + teacher.getSalary() +
				", hireDate=" + teacher.getHireDate() +
				", isFullTime=" + teacher.getIsFullTime() +
				", description='" + teacher.getDescription() + '\'' +
				", createTime=" + teacher.getCreateTime() +
				", updateTime=" + teacher.getUpdateTime() +
				"}}";
	}
}
