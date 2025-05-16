package com.example.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component(value = "myStudent")
public class Student {

	@Value("游家纨绔")   // 或者加在set方法上
	private String name;
	@Value("2020915")  // 或者加在set方法上
	private int age;


	public Student() {
		System.out.println("Student的无参构造");
	}

	// @Value("游家纨绔")
	public void setName(String name) {
		this.name = name;
	}

	// @Value("2020915")
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
