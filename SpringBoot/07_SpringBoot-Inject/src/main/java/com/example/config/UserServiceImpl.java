package com.example.config;

import com.example.mapper.StudentMapper;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-12 12:00
 * @apiNote TODO 通过 @Bean 的方式注入容器
 */
public class UserServiceImpl implements UserService {

	private StudentMapper studentMapper;

	// TODO 从这里可以发现，该 Bean 中属性只有 setter 方法，没有 被构造方法注入，也没有 @Autowired 注解标注
	//      那么这里的 studentMapper 属性值按理来说为 null。实际上这里的 studentMapper 是存在值的。
	//      原因就在于 @Bean 设置的属性 autowired，会根据UserServiceImpl属性的 setter 方法完成自动注入
	public void setStudentMapper(StudentMapper studentMapper) {
		this.studentMapper = studentMapper;
	}

	public void test() {
		System.out.println("使用@Bean 中的 autowire 属性，得到 studentMapper 值：" + studentMapper);
	}
}
