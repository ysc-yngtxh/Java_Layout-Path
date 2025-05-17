package com.example.io;

import java.io.InputStream;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-02 16:50:00
 * @apiNote TODO 读取 classpath 路径下的文件流
 */
public class Resources {

	public static InputStream getResourceAsStream(String fileName) {
		// 通过当前线程的类加载器，获取 classpath 路径下的文件流
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	}

}
