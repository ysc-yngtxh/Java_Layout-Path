package com.example.spi.dubbo;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-19 08:00
 * @apiNote TODO
 */
public class Motorcycle implements Car {

	@Override
	public void sayHello() {
		System.out.println("Hello, I am Motorcycle.");
	}
}
