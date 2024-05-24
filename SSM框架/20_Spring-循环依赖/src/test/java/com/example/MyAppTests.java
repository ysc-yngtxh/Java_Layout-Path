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

        // 调用aService的方法
        AService aService = applicationContext.getBean("aService", AService.class);
        aService.testProxy();

        // 调用bService的方法
        BService bService = applicationContext.getBean("bService", BService.class);
        bService.sayHello();

        // TODO 通过以上程序，需要注意几个点：
        //  1、通过打断点可以知道 aService 是CGLIB代理对象，且 aService 中有一个属性 bService 为null
        //  2、正常来说，执行 bService 中的 sayHello() 方法打印的应该是 半成品bean(aService 还未初始化完成)
        //     但实际上，打印的结果是 aService 的CGLIB代理对象。这也侧面说明，Spring循环依赖的第三级缓存起作用了。
        //     aService 最终创建完成后在单例池(一级缓存)中是 CGLIB代理对象，用以替代原本的目标对象 aService 实例。
    }
}
