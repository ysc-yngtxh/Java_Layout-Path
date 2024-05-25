package com.example;

import com.example.bao1.OneService;
import com.example.bao2.TwoService;
import com.example.bao3.ThreeService;
import com.example.bao4.FourService;
import com.example.cglib.CglibServiceImpl;
import com.example.jdk.JdkService;
import com.example.proxy.ProxyService;
import com.example.proxyAttribute.AttrServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyAppTests {

    // bao1中的AspectJ(封装好了的jdk动态代理)，前置通知切面功能
    @Test
    public void text01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从容器中获取目标
        OneService proxy = ac.getBean("Service1", OneService.class);
        System.err.println(
                "Spring识别到目标类进行了增强功能Aop，因此这里获取到的目标类是jdk的代理对象，而不是实例对象。\n" +
                "这个代理对象不是该实现类的实例，而是它实现的接口的实例。因此在代理对象进行类型转换时，应该转换为接口的实例，而不是具体实现类的实例。\n" +
                "并且代理对象是在目标对象属性注入之后、初始化之前 创建的 " + proxy.getClass().getName()
        );
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.doSome("曹玉敏我喜欢你", 23);
    }

    // bao2中的AspectJ，后置通知切面功能
    @Test
    public void text02(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从容器中获取目标
        TwoService proxy = ac.getBean("Service2", TwoService.class);
        System.err.println(
                "Spring识别到目标类进行了增强功能Aop，因此这里获取到的目标类是jdk的代理对象，而不是实例对象。\n" +
                "这个代理对象不是该实现类的实例，而是它实现的接口的实例。因此在代理对象进行类型转换时，应该转换为接口的实例，而不是具体实现类的实例。\n" +
                "并且代理对象是在目标对象属性注入之后、初始化之前 创建的 " + proxy.getClass().getName()
        );
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        String str = proxy.doOther("曹玉敏我喜欢你", 2020917);
        System.out.println("str = " + str);
    }

    // bao2中的AspectJ，引用类型(Student)
    @Test
    public void text03(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从容器中获取目标
        TwoService proxy = ac.getBean("Service2", TwoService.class);
        System.err.println(
                "Spring识别到目标类进行了增强功能Aop，因此这里获取到的目标类是jdk的代理对象，而不是实例对象。\n" +
                "这个代理对象不是该实现类的实例，而是它实现的接口的实例。因此在代理对象进行类型转换时，应该转换为接口的实例，而不是具体实现类的实例。\n" +
                "并且代理对象是在目标对象属性注入之后、初始化之前 创建的 " + proxy.getClass().getName()
        );
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        proxy.student("曹玉敏我喜欢你", 2020917);
    }

    // bao3中的AspectJ，环绕通知切面功能
    @Test
    public void text04(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 从容器中获取目标
        ThreeService proxy = ac.getBean("Service3", ThreeService.class);
        System.err.println(
                "Spring识别到目标类进行了增强功能Aop，因此这里获取到的目标类是jdk的代理对象，而不是实例对象。\n" +
                "这个代理对象不是该实现类的实例，而是它实现的接口的实例。因此在代理对象进行类型转换时，应该转换为接口的实例，而不是具体实现类的实例。\n" +
                "并且代理对象是在目标对象属性注入之后、初始化之前 创建的 " + proxy.getClass().getName()
        );
        // 通过代理的对象执行方法，实现目标方法执行时，增强了功能
        String str = proxy.First("曹玉敏我喜欢你", 2020917);
        System.out.println("str=" + str);
    }

    // bao4中的AspectJ,概括性的总结
    @Test
    public void text05(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        FourService proxy = ac.getBean("Service4", FourService.class);
        System.err.println(
                "Spring识别到目标类进行了增强功能Aop，因此这里获取到的目标类是jdk的代理对象，而不是实例对象。\n" +
                "这个代理对象不是该实现类的实例，而是它实现的接口的实例。因此在代理对象进行类型转换时，应该转换为接口的实例，而不是具体实现类的实例。\n" +
                "并且代理对象是在目标对象属性注入之后、初始化之前 创建的 " + proxy.getClass().getName()
        );
        proxy.like("曹玉敏，我喜欢你!", 23);
    }

    // cglib包中没有接口是cglib代理(有接口默认是jdk代理，没有接口默认是cglib代理)
    @Test
    public void text06(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        CglibServiceImpl proxy = ac.getBean("cglibService", CglibServiceImpl.class);
        System.err.println(
                "Spring识别到目标类进行了增强功能Aop，因此这里获取到的目标类是cglib的代理对象，而不是实例对象。\n" +
                "那么这个代理对象就是该实现类的实例。因此在代理对象进行类型转换时，应该转换为具体实现类的实例。\n" +
                "并且代理对象是在目标对象属性注入之后、初始化之前 创建的 " + proxy.getClass().getName()
        );
        proxy.like("曹玉敏，我喜欢你!", 23);
    }

    // jdk包下存在接口，表示为jdk动态代理
    @Test
    public void text07(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdkService proxy = ac.getBean("jdkService", JdkService.class);
        System.err.println(
                "Spring识别到目标类进行了增强功能Aop，因此这里获取到的目标类是jdk的代理对象，而不是实例对象。\n" +
                "这个代理对象不是该实现类的实例，而是它实现的接口的实例。因此在代理对象进行类型转换时，应该转换为接口的实例，而不是具体实现类的实例。\n" +
                "并且代理对象是在目标对象属性注入之后、初始化之前 创建的 " + proxy.getClass().getName()
        );
        proxy.like("曹玉敏，我喜欢你!", 23);
    }

    // 控制台的结果没有切面编程的打印。因为Aop是基于动态代理实现的，这里this调用直接对象，Aop不生效
    @Test
    public void text08(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProxyService proxy = ac.getBean("proxyService", ProxyService.class);
        System.err.println(
                "Spring识别到目标类进行了增强功能Aop，因此这里获取到的目标类是jdk的代理对象，而不是实例对象。\n" +
                "这个代理对象不是该实现类的实例，而是它实现的接口的实例。因此在代理对象进行类型转换时，应该转换为接口的实例，而不是具体实现类的实例。\n" +
                "并且代理对象是在目标对象属性注入之后、初始化之前 创建的 " + proxy.getClass().getName()
        );
        proxy.saveSigUser();
    }

    // 获取当前执行程序的代理对象，通过代理对象调用，Aop生效（需要在xml文件中开启expose-proxy）
    @Test
    public void text09(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProxyService proxy = ac.getBean("proxyService", ProxyService.class);
        System.err.println(
                "Spring识别到目标类进行了增强功能Aop，因此这里获取到的目标类是jdk的代理对象，而不是实例对象。\n" +
                "这个代理对象不是该实现类的实例，而是它实现的接口的实例。因此在代理对象进行类型转换时，应该转换为接口的实例，而不是具体实现类的实例。\n" +
                "并且代理对象是在目标对象属性注入之后、初始化之前 创建的 " + proxy.getClass().getName()
        );
        proxy.saveAllUser();
    }

    // 代理对象中的属性值并没有进行依赖注入
    @Test
    public void text10(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AttrServiceImpl proxy = ac.getBean("attrService", AttrServiceImpl.class);
        System.err.println(
                "由于 AttrServiceImpl 被 Aop切面，所以这里获取的是代理对象。\n" +
                "可以通过打断点的方式，在控制台查看代理对象 proxy 的属性数据 injectService 值为 null.\n" +
                "但是 AttrServiceImpl 的原始对象是进行了依赖注入的，属性 injectService 应该是是存在值的。\n" +
                "之所以出现这个情况，是因为代理对象并没有对任何属性进行依赖注入，因此自然为null.\n"
                + proxy.getClass().getName()
        );

        proxy.doSomething(); // 通过代理对象执行方法

        System.err.println(
                "这里你肯定会很奇怪：为什么通过代理对象调用的方法中打印的属性却有值？\n" +
                "因为代理对象可以看做是继承目标对象的一个新的类，\n" +
                "public class proxy extends AttrServiceImpl {\n" +
                "    private injectServiceImpl target;\n" +
                "    public void doSomething() {\n" +
                "        // 在这里执行切面前置逻辑......\n" +
                "        target.doSomething(); // 这里执行目标对象的方法\n" +
                "        // 在这里执行切面后置逻辑......\n" +
                "    }\n" +
                "}\n" +
                "因此代理对象中存在一个属性 target，指向目标对象。而目标对象中的属性 injectService 已经被注入了值。\n"
        );
    }
}
