package com.example.spi.jdk;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:00
 * @apiNote TODO
 */
public class Dog implements Animals {

	@Override
	public void sayHello() {
		System.out.println("Hello, I am Dog.");
	}
}
