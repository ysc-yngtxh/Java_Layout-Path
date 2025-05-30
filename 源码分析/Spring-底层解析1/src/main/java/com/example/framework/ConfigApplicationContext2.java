package com.example.framework;

import com.example.framework.annotation.Autowired;
import com.example.framework.annotation.Component;
import com.example.framework.annotation.ComponentScan;
import com.example.framework.annotation.Scope;
import com.example.framework.interfaces.BeanNameAware;
import com.example.framework.interfaces.BeanPostProcessor;
import com.example.framework.interfaces.InitializingBean;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.SneakyThrows;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-07 23:10:00
 * @apiNote TODO Spring容器
 */
public class ConfigApplicationContext2 {

	// 配置类
	private Class<?> rootClass;

	// 单例池
	private Map<String, Object> singletonBeanMap = new HashMap<>();
	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
	private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

	public ConfigApplicationContext2(Class<?> rootClass) {
		this.rootClass = rootClass;

		// 流程：解析配置类
		// [ComponentScan注解 ---> 扫描路径 ---> 扫描类文件 ---> BeanDefinition ---> BeanDefinitionMap]

		// 扫描
		parseScan(rootClass);

		// 实例化Bean，并注册到单例池中
		for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
			BeanDefinition beanDefinition = entry.getValue();
			if (beanDefinition.getScope().equals("singleton")) {
				Object bean = createBean(entry.getKey(), beanDefinition); // 单例 Bean
				singletonBeanMap.put(entry.getKey(), bean);
			}
		}
	}

	@SneakyThrows
	private void parseScan(Class<?> rootClass) {
		// TODO 1、通过获取 @ComponentScan 注解，获取扫描路径
		ComponentScan componentScanAnnotation = rootClass.getDeclaredAnnotation(ComponentScan.class);
		String scanPath = componentScanAnnotation.value();

		// TODO 2、扫描路径下的所有类
		// 获取当前类加载器
		ClassLoader classLoader = ConfigApplicationContext2.class.getClassLoader();
		// 通过类加载器获取扫描路径的下的资源 URL (绝对地址)
		URL url = classLoader.getResource(scanPath.replace(".", File.separator));
		// 将 URL 进行中文解码 (避免路径中存在中文乱码)
		String urlDecodePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);

		// 创建 File 对象
		File file = new File(urlDecodePath);
		// 获取该项目的 target/classes 目录下的文件路径
		String resourcePath = this.getClass().getResource("/").toURI().getPath();
		// 判断该地址下的文件是否为目录
		if (file.isDirectory()) {
			File[] files = file.listFiles();  // 获取 File 对象下的所有文件
			for (File f : files) {
				// 获取文件的绝对路径
				String fileName = f.getPath();
				// 筛选出文件的相对路径
				String classPath = fileName.replace(resourcePath, "")
				                           .replace(".class", "")
				                           .replace(File.separator, ".");
				// ComponentScan的路径是包路径，这里的是文件路径。加载相对路径的类文件
				Class<?> aClass = classLoader.loadClass(classPath);

				// TODO 3、解析类上的注解，将类的相关元信息封装为 BeanDefinition 并存入 beanDefinitionMap 中
				// 判断该类上是否存在 @Component 注解
				if (aClass.isAnnotationPresent(Component.class)) {
					// 判断该类为单例Bean 还是原型(protoType) Bean
					// 1、作为一个单例Bean来说，我们在调用 getBean() 方法时可以直接从单例池中取出 Bean对象
					// 2、作为一个原型Bean来说，每次调用 getBean() 方法都需要创建一个新的 Bean 对象
					//    Spring 在创建一个新的 Bean 对象时，getBean(String beanName) 中的 beanName 具体是哪个类，无法得知。
					//    小曹会说这个名称就是类名的首字母小写；那如果我在 @Component("自定义名称") Spring 又该咋办？
					//    而且，就算没有自定义名称，那万一出现了不同包下同一个类名的文件咋办？Spring 应该获取哪个类的Bean？
					//    由此，Spring 引出了一个 BeanDefinition 的概念

					Component componentAnnotation = aClass.getDeclaredAnnotation(Component.class);
					String beanName = componentAnnotation.value();
					// BeanDefinition
					BeanDefinition beanDefinition = new BeanDefinition();
					beanDefinition.setClazz(aClass);
					if (aClass.isAnnotationPresent(Scope.class)) {
						Scope scopeAnnotation = aClass.getDeclaredAnnotation(Scope.class);
						beanDefinition.setScope(scopeAnnotation.value());
					} else {
						// 获取 @Scope 注解属性 value 的默认值
						String defaultValue = Scope.class.getDeclaredMethods()[0].getDefaultValue().toString();
						beanDefinition.setScope(defaultValue);
					}
					beanDefinitionMap.put(beanName, beanDefinition);

					// 判断该类是否实现了 BeanPostProcessor 接口。不能使用 instanceof，因为这里判断的是类不是对象
					if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
						BeanPostProcessor beanPostProcessor = (BeanPostProcessor) aClass.getDeclaredConstructor().newInstance();
						beanPostProcessorList.add(beanPostProcessor);
					}
				}
			}
		}
	}

	@SneakyThrows
	private Object createBean(String beanName, BeanDefinition beanDefinition) {
		Class<?> clazz = beanDefinition.getClazz();
		Object instance = clazz.getDeclaredConstructor().newInstance();
		// 属性(依赖)注入
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(Autowired.class)) {
				// 在这里可以处理 byName、byType 注入；这里只是简单实现 byName
				Object bean = getBean(field.getName());
				if (Objects.isNull(bean)) {
					throw new NullPointerException("该类没有找到对应的 Bean");
				}
				field.setAccessible(true);
				field.set(instance, bean);
			}
		}

		// Aware回调
		if (instance instanceof BeanNameAware) {
			((BeanNameAware) instance).setBeanName(beanName);
		}

		// 调用 BeanPostProcessor 初始化前的后置处理
		for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
			instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
		}

		// 初始化
		if (instance instanceof InitializingBean) {
			((InitializingBean) instance).afterPropertiesSet();
		}

		// 调用 BeanPostProcessor 初始化后的后置处理
		for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
			instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
		}

		return instance;
	}

	public Object getBean(String beanName) {
		if (beanDefinitionMap.containsKey(beanName)) {
			BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
			// 判断该 BeanDefinition 是否为单例 Bean
			if (beanDefinition.getScope().equals("singleton")) {
				return singletonBeanMap.get(beanName);
			} else {
				return createBean(beanName, beanDefinition);
			}
		} else {
			throw new NullPointerException("BeanDefinition 不存在, 请检查配置类是否正确");
		}
	}

}
