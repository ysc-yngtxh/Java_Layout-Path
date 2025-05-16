package com.example.load;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.SneakyThrows;

/**
 * @author: 游家纨绔
 * @date: 2025/5/8 23:00:00
 * @desc: TODO 自定义普通加载类
 */
public class CustomClassLoader extends ClassLoader {

	private final String classPath;

	public CustomClassLoader(String classPath) {
		this.classPath = classPath;
	}

	// 重写 findClass 方法，实现自定义类加载逻辑
	@SneakyThrows
	@Override
	public Class<?> findClass(String name) {
		// 1、构建类文件路径（使用 Path 替代字符串拼接，更安全）
		Path classFilePath = Paths.get(
				classPath,
				"target",
				"classes",
				name.replace('.', File.separatorChar) + ".class"
		                              );
		// 2、读取字节码文件（假设已编译为 .class 文件）
		byte[] classData = Files.readAllBytes(classFilePath);
		// 3. 检查数据是否为空（虽然 readAllBytes 通常不会返回 null，但安全起见）
		if (classData == null || classData.length == 0) {
			throw new ClassNotFoundException("Empty or invalid class data: " + name);
		}
		// 4、调用 defineClass() 方法将字节数组转换为 Class 对象
		return defineClass(name, classData, 0, classData.length);
	}
}
