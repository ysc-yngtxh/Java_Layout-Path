package com.example;

import com.example.service.AService;
import com.example.service.BService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyAppTests {

	@Test
	public void test() {
		String xmlPath = "applicationContext.xml";
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);

		// TODO 通过打断点可以知道 aService 是CGLIB代理对象，且 aService 中有一个属性 bService 为null
		//      注意：代理对象并没有对任何属性进行依赖注入。因此这里 代理对象 中属性 bService 为 null
		AService aService = applicationContext.getBean("aService", AService.class);
		aService.testProxy();

		// TODO 正常来说，执行 bService 中的 sayHello() 方法打印的应该是 aService原始对象。
		//      但实际上，由于 aService 被 Aop 切面，打印的结果是 aService 的 CGLIB代理对象。
		//      这也说明，Spring循环依赖的第三级缓存起作用了。(第三级缓存保证了Aop循环依赖下，代理对象与做为属性注入的目标对象是一致的)
		//      aService 最终创建完成后在单例池(一级缓存)中是 CGLIB代理对象，用以替代原本的目标对象 aService 实例。
		BService bService = applicationContext.getBean("bService", BService.class);
		bService.sayHello();
	}
}
