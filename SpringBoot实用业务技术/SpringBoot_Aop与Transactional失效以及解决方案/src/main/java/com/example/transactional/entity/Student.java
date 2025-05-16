package com.example.transactional.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Student)表实体类
 *
 * @author 游家纨绔
 * @since 2023-11-11 17:30:00
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
	@Serial
	private static final long serialVersionUID = 7411154017460139062L;

	private Integer id;

	private String name;

	private String email;

	private Integer age;
}
