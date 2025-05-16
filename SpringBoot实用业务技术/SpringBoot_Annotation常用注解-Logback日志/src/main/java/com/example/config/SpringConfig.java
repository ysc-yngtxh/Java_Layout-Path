package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

// 可以在@Component、@Service、@Controller、@Repository注解中发现其子注解：@Indexed
// @Indexed注解：随着项目越来越复杂，那么@ComponentScan需要扫描加载的Class会越来越多，在系统启动的时候会造成性能损耗。
//              所以在系统编译的时候会收集所有被@Indexed注解修饰的类，然后将这些类信息记录在MATE-INF/spring.components文件中，
//              那么系统启动的时候就只需要读取一个该文件中的内容就不用再遍历所有的目录了，提升效率。
//              使用：只需在pom文件中添加 spring-context-indexer 依赖。
@Order(-1) // 定义Spring IOC容器中Bean的执行顺序的优先级：值越小执行顺序优先级越高
@Configuration
public class SpringConfig {

	// @DependsOn注解可以定义在类和方法上，可以配置Spring IOC容器在初始化一个Bean之前，先初始化其他的Bean对象。
	// 比如正常情况下，Bean初始化为（事件源创建 -> 监听器创建），加上该注解后Bean初始化为（监听器创建 -> 事件源创建）
	// 前提是给定先初始化的Bean对象必须存在能够注入Spring容器中
	@Bean
	@DependsOn(value = {"eventListener"})
	public EventSource eventSource() {
		return new EventSource();
	}

	// @Scope注解可以用来定义@Component标注的类的作用范围以及@Bean所标记的类的作用范围。
	// @Scope所限定的作用范围有：singleton、prototype、request、session、globalSession或者其他的自定义范围。
	// 声明为prototype（原型模式）时，在每次需要使用到该类的时候，Spring IoC容器都会初始化一个新的改类的实例。
	@Bean
	@Scope(value = "prototype")
	public EventListener eventListener() {
		return new EventListener();
	}

	public class EventSource {
		public EventSource() {
			System.out.println("事件源创建");
		}
	}

	public class EventListener {
		public EventListener() {
			System.out.println("监听器创建");
		}
	}
}
