package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple MavenApp.
 */
public class MavenAppTest {

	@Test
	public void testAdd() {
		System.out.println("==== testAdd 测试add方法 ====");
		MavenApp hello = new MavenApp();
		int res = hello.add(10, 20);
		// 判断结果是否正确
		Assertions.assertEquals(30, res);
	}


	@Test
	public void testAdd2() {
		System.out.println("==== testAdd2 测试add方法 ====");
		MavenApp hello = new MavenApp();
		int res = hello.add(10, 20);
		// 判断结果是否正确
		Assertions.assertEquals(30, res);
	}

}
