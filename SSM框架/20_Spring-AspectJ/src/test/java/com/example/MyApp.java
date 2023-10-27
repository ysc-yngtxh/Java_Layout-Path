package com.example;

import com.example.bao1.SomeServiceImpl;
import com.example.bao2.OtherService;
import com.example.bao3.FirstService;
import com.example.bao4.OutService;
import com.example.cglib1.CglibServiceImpl;
import com.example.jdk.CglibByServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {
    // bao1中的AspectJ(封装好了的jdk动态代理)，前置通知切面功能
    @Test
    public void text01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从容器中获取目标
        SomeServiceImpl proxy = ac.getBean("Service1", SomeServiceImpl.class);
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.doSome("曹玉敏我喜欢你", 23);
    }

    // bao2中的AspectJ，后置通知切面功能
    @Test
    public void text02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从容器中获取目标
        OtherService proxy = (OtherService) ac.getBean("Service2");
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        String str = proxy.doOther("曹玉敏我喜欢你", 2020917);
        System.out.println("str=" + str);
    }

    // bao2中的AspectJ，引用类型(Student)
    @Test
    public void text03(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从容器中获取目标
        OtherService proxy = (OtherService) ac.getBean("Service2");
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.student("曹玉敏我喜欢你", 2020917);
    }

    // bao3中的AspectJ，环绕通知切面功能
    @Test
    public void text04(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从容器中获取目标
        FirstService proxy = (FirstService) ac.getBean("Service3");
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        String str = proxy.First("曹玉敏我喜欢你", 2020917);
        System.out.println("str=" + str);
    }

    // bao4中的AspectJ,概括性的总结
    @Test
    public void text05(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        OutService proxy = (OutService) ac.getBean("Service4");

        System.out.println("proxy:" + proxy);
        proxy.like("曹玉敏，我喜欢你!", 23);
    }

    // cglib1包中没有接口是cglib代理(有接口默认是jdk代理，没有接口默认是cglib代理)
    @Test
    public void text06(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CglibServiceImpl proxy = (CglibServiceImpl) ac.getBean("Service5");

        System.out.println("proxy:" + proxy);
        proxy.like("曹玉敏，我喜欢你!", 23);
    }

    @Test
    public void text07(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CglibByServiceImpl proxy = (CglibByServiceImpl) ac.getBean("Service6");

        System.out.println("proxy:" + proxy);
        proxy.like("曹玉敏，我喜欢你!", 23);
    }
}
