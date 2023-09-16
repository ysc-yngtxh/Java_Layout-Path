package com.bjpowernode;

import com.bjpowernode.ba01.SomeServiceImpl;
import com.bjpowernode.ba02.OtherService;
import com.bjpowernode.ba03.FirstService;
import com.bjpowernode.ba04.OutService;
import com.bjpowernode.cglib1.CglibServiceImpl;
import com.bjpowernode.jdk.CglibByServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {

    //ba01中的AspectJ(封装好了的jdk动态代理)，前置通知切面功能
    @Test
    public void text01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中获取目标
        SomeServiceImpl proxy = ac.getBean("Service1", SomeServiceImpl.class);
        //通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.doSome("曹玉敏我喜欢你", 23);
    }

    //ba02中的AspectJ，后置通知切面功能
    @Test
    public void text02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中获取目标
        OtherService proxy = (OtherService)ac.getBean("Service2");
        //通过代理的对象执行方法，实现目标方法执行时，增强了功能
        String str = proxy.doOther("曹玉敏我喜欢你", 2020917);
        System.out.println("str="+str);
    }

    //ba02中的AspectJ，引用类型(Student)
    @Test
    public void text03(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中获取目标
        OtherService proxy = (OtherService)ac.getBean("Service2");
        //通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.student("曹玉敏我喜欢你", 2020917);
    }

    //ba03中的AspectJ，环绕通知切面功能
    @Test
    public void text04(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中获取目标
        FirstService proxy = (FirstService)ac.getBean("Service3");
        //通过代理的对象执行方法，实现目标方法执行时，增强了功能
        String str = proxy.First("曹玉敏我喜欢你", 2020917);
        System.out.println("str="+str);
    }

    //ba04中的AspectJ,概括性的总结
    @Test
    public void text05(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        OutService proxy = (OutService)ac.getBean("Service4");

        System.out.println("proxy:"+proxy);
        proxy.like("曹玉敏，我喜欢你!", 23);
    }

    //cglib1包中没有接口是cglib代理(有接口默认是jdk代理，没有接口默认是cglib代理)
    @Test
    public void text06(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CglibServiceImpl proxy = (CglibServiceImpl)ac.getBean("Service5");

        System.out.println("proxy:"+proxy);
        proxy.like("曹玉敏，我喜欢你!", 23);
    }

    //
    @Test
    public void text07(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CglibByServiceImpl proxy = (CglibByServiceImpl)ac.getBean("Service6");

        System.out.println("proxy:"+proxy);
        proxy.like("曹玉敏，我喜欢你!", 23);
    }
}
