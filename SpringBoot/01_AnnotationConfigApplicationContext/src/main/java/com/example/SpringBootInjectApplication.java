package com.example;

import com.example.config.AppConfig;
import com.example.pojo.Hello;
import com.example.pojo.User;
import com.example.pojo.World;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBootInjectApplication {

	public static void main(String[] args) {

		// 创建一个AnnotationConfigApplicationContext的实例，并传入AppConfig.class作为参数。
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(AppConfig.class);

		// 获取bean
		System.out.println("获取注册的 Bean：" + applicationContext.getBean("myUser", User.class));

		// registerBean方法允许注册一个bean定义，可以配置bean的类、构造函数参数、作用域和其他属性。
		// 使用registerBean方法注册的bean可以在ApplicationContext容器中注入其他依赖、使用Autowiring等Spring功能。
		applicationContext.registerBean("hello", Hello.class);

		// registerSingleton方法用于注册一个单例实例的bean，即直接将一个已经实例化的对象注册到Spring容器中。
		// 这个对象将被视为单例，即每次从容器中获取该bean时都返回同一个实例。
		// 使用registerSingleton方法注册的bean不能配置构造函数参数、作用域等属性，只能简单地注册一个实例。
		applicationContext.getBeanFactory().registerSingleton("world", new World());

		// 获取bean
        System.out.println("获取注册的 Bean：" + applicationContext.getBean("hello", Hello.class));
		System.out.println("获取注册的 Bean：" + applicationContext.getBean("world", World.class));
	}

}
