package com.example;

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
        // JDK的SPI机制
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        // serviceLoader.forEach(Robot::sayHello);
        Iterator<Robot> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Robot robot = iterator.next();
            System.out.println(robot);
            robot.sayHello();
        }

        System.out.println("=======================================================");

        // Spring的SPI机制
        List<Robot> robots = SpringFactoriesLoader.loadFactories(Robot.class, null); // 第二个参数为null表示使用当前的类加载器
        robots.forEach(Robot::sayHello);
    }
}
