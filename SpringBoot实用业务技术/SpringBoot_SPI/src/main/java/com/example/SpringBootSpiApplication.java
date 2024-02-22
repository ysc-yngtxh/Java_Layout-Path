package com.example;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.example.spi.Robot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;


@SpringBootApplication
public class SpringBootSpiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSpiApplication.class, args);
    }

    // TODO SPI 全称为“Service Provider Interface”，是一种服务发现机制。
    //      SPI的本质是将接口实现类的全限定名配置在文件中，并由服务加载器读取配置文件，加载实现类。
    //      这样可以在运行时，动态为接口替换实现类

    @Override
    public void run(String... args) throws Exception {
        // TODO JDK的SPI机制
        //      缺陷：1、不能按需加载：需要遍历所有实现并实例化，然后在循环中找到我们需要的实现。如果不想用某些实现类，
        //                         或者某些类实例化很耗时，它也被载入并实例化了，就会造成浪费
        //           2、获取某个实现类的方式不够灵活：只能通过迭代器形式获取，不能根据某个参数来获取对应的实现类
        //           3、多个并发多线程使用ServiceLoader类的实例是不安全的
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        // serviceLoader.forEach(Robot::sayHello);
        Iterator<Robot> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Robot robot = iterator.next();
            System.out.println(robot);
            robot.sayHello();
        }

        System.out.println("=======================================================");

        // TODO Spring SPI沿用了Java SPI的设计思想，Spring采用的是‘spring.factories’方式实现SPI机制，
        //      可以在不修改Spring源码的前提下，提供Spring框架的扩展性
        //      缺陷：和Java SPI一样，Spring SPI也无法获取某个固定的实现，只能按顺序获取所有实现
        // List<Robot> robots = SpringFactoriesLoader.loadFactories(Robot.class, Thread.currentThread().getContextClassLoader());
        List<Robot> robots = SpringFactoriesLoader.loadFactories(Robot.class, null); // 第二个参数为null表示使用当前的类加载器
        robots.forEach(Robot::sayHello);

        System.out.println("=======================================================");

        // TODO Dubbo SPI 除了支持按需加载接口实现类，还增加了 IOC和AOP 等特性
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        bumblebee.sayHello();
        optimusPrime.sayHello();
    }
}
