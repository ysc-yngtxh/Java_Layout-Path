package com.example.vo;

import com.alibaba.fastjson2.annotation.JSONField;
import java.util.Date;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @description: TODO
 * @date 2022/11/26 19:00:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Long id;
	private String name;
	private String email;

	@JSONField(name = "AGE")
	private int age;

	private Date date;
	private Models models;
	private Optional<Models> modelsOptional;

	public User(Long id, String name, String email, int age) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "User{" +
				"Id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", age=" + age +
				", models=" + models +
				", modelsOptional=" + modelsOptional +
				'}';
	}
}
