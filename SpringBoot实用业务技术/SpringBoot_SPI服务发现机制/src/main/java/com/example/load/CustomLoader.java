package com.example.load;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.core.io.ClassPathResource;

public class CustomLoader<T> {

		private static Map<String, String> customMap = new ConcurrentHashMap<>();

		public CustomLoader(Map<String, String> customMap) {
			CustomLoader.customMap = customMap;
		}

		// TODO 方式一
		public static <T> CustomLoader<T> getCustomLoader(Class<T> clazz) throws IOException {
			// 获取 class 类路径
			String classPath = clazz.getName();
			// 获取相应的资源
			ClassPathResource resource = new ClassPathResource("META-INF/custom/" + classPath);
			InputStream inputStream = resource.getInputStream();
			// 构造 Properties 对象
			Properties properties = new Properties();
			// 将指定的资源加载到 Properties 对象中
			properties.load(inputStream);
			Map<String, String> map = new HashMap<>();
			properties.forEach((proKey, proValue) -> {
				map.put((String) proKey, (String) proValue);
			});
			return new CustomLoader<>(map);
		}

		// TODO 方式二
		public static <T> CustomLoader<T> getCustomLoaderInputStream(Class<T> clazz) throws IOException {
			// 获取 class 类路径
			String classPath = clazz.getName();
			// 获取相应的资源
			ClassPathResource resource = new ClassPathResource("META-INF/custom/" + classPath);
			InputStream inputStream = resource.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			Map<String, String> map = new HashMap<>();
			if ((line = bufferedReader.readLine()) != null) {
				if (line.length() > 1) {
					String[] split = line.split("=");
					map.put(split[0].trim(), split[1].trim());
				}
			}
			return new CustomLoader<>(map);
		}

		public T getCustomClass(String className, Class<T> classType) {
			if (!customMap.containsKey(className)) {
				return null;
			}
			try {
				Class<?> clazz = Class.forName(customMap.get(className));
				Object instance = clazz.getDeclaredConstructor().newInstance();
				return classType.cast(instance); // 安全类型转换
			} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
				throw new RuntimeException("Failed to instantiate class: " + className, e);
			}
		}
	}
