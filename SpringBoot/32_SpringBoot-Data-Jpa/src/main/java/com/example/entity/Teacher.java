package com.example.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2025-08-23 15:30
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // 指定表名为 db_student
// 如果不指定表名，则默认使用类名作为表名，且首字母小写
// 如果需要使用 @Table 注解来指定表名，可以取消注释下面
@Table(name = "db_teacher")
public class Teacher {

	private Integer id;
	private String teacherId;
	private String name;
	private Integer gender;
	private Integer age;
	private String email;
	private String phone;
	private Integer departmentId;
	private String title;
	private BigDecimal salary;
	private LocalDate hireDate;
	private Boolean isFullTime;
	private String description;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;

}
