package com.example.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 @author 游家纨绔
 @dateTime 2025-08-22 08:00
 @apiNote TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// @Entity(必选)：一个类要被持久化，就必须使用此注解。用于标记该类为JPA实体类。name 属性用于指定实体对应的数据库表名【可选】
@Entity
// @Table(可选)：用于指定实体类所映射的数据库表的信息。如果不使用 @Table 注解，则默认使用类名作为表名，且首字母小写
//      name 属性用于指定表名，schema 属性用于指定数据库模式，catalog 属性用于指定数据库目录。
//      uniqueConstraints 用于指定表中的唯一约束，可以有多个。indexes 用于指定表中的索引，可以有多个。
// uniqueConstraints 和 indexes 属性主要被Hibernate等 JPA提供商 的自动建表功能（如 hibernate.hbm2ddl.auto）所使用。
// 如果你手动管理数据库表结构，这些注解不会影响现有表，它们只是作为元数据存在。
@Table(name = "db_student", schema = "inventory", catalog = "store",
	   uniqueConstraints = {
		       @UniqueConstraint(columnNames = {"serialNumber"})
	   },
	   indexes = {
		       // 该注解会指示JPA在自动建表时创建三个索引。
			   //    name：索引的名称（可选）。
			   //    columnList：要创建索引的列名，多个列用逗号分隔。可以指定排序方式（ASC, DESC）。
			   //    unique：是否为唯一索引，默认为 false
		       @Index(name = "idx_order_customer_id", columnList = "customer_id"),
			   @Index(name = "idx_order_date_desc", columnList = "order_date DESC"),
			   @Index(columnList = "status, customer_id", unique = false)
       })
public class Student implements Serializable {
	private static final long serialVersionUID = -6186149606519563816L;

	@Id
	@GeneratedValue(strategy= GenerationType.TABLE)
	private Integer id;

	private String name;

	private String email;

	private Integer age;

}
