package com.example.v1.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * (Student)实体类
 *
 * @author 游家纨绔
 * @since 2024-04-04 23:50:00
 */
@Data
@ToString
public class Student implements Serializable {
	@Serial
	private static final long serialVersionUID = 884930802771289886L;

	private Integer id;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 年龄
	 */
	private String age;
}
