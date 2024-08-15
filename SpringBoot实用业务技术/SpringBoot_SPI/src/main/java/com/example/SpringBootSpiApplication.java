package com.example;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.example.spi.custom.Color;
import com.example.spi.dubbo.Car;
import com.example.spi.jdk.Animals;
import com.example.spi.spring.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.SpringFactoriesLoader;

@SpringBootApplication
public class SpringBootSpiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootSpiApplication.class, args);
        List<String> collect = Arrays.stream(context.getBeanDefinitionNames())
                .collect(Collectors.toList());
        System.out.println("customTemplate是否存在：" + collect.contains("customTemplate"));
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
        ServiceLoader<Animals> serviceLoader = ServiceLoader.load(Animals.class);
        // serviceLoader.forEach(Animals::sayHello);
        Iterator<Animals> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            // 注意：hasNext()方法会去寻找下一个服务实现类，next()方法才会利用反射实例化该实现类，起到一种懒加载的作用
            Animals animals = iterator.next();
            System.out.println(animals);
            animals.sayHello();
        }


        System.out.println("=======================================================");

        // TODO Spring SPI沿用了Java SPI的设计思想，Spring采用的是 'spring.factories' 方式实现SPI机制，
        //      可以在不修改Spring源码的前提下，提供Spring框架的扩展性。
        //   区别：在是否实例化实现类的层面上，SpringBoot会依据@Conditional注解来判断是否进行实例化并注入进容器中，
        //        而jdk会在next方法内部懒加载实现类。
        //   缺陷：和Java SPI一样，Spring SPI也无法获取某个固定的实现，只能按顺序获取所有实现
        // List<Robot> robots = SpringFactoriesLoader.loadFactories(Robot.class, Thread.currentThread().getContextClassLoader());
        List<Robot> robots = SpringFactoriesLoader.loadFactories(Robot.class, null); // 第二个参数为null表示使用当前的类加载器
        robots.forEach(Robot::sayHello);


        System.out.println("=======================================================");

        // TODO Dubbo SPI 除了支持按需加载接口实现类，还增加了 IOC和AOP 等特性
        ExtensionLoader<Car> extensionLoader = ExtensionLoader.getExtensionLoader(Car.class);
        Car bus = extensionLoader.getExtension("bus");
        Car motorcycle = extensionLoader.getExtension("motorcycle");
        bus.sayHello();
        motorcycle.sayHello();


        System.out.println("=======================================================");

        // TODO 自定义 SPI 实现：按需加载
        CustomLoader<Color> customLoader = CustomLoader.getCustomLoader(Color.class);
        Color red = customLoader.getCustomClass("red");
        Color blue = customLoader.getCustomClass("blue");
        red.takeColor();
        blue.takeColor();
    }


    public static class CustomLoader<T> {

        private static Map<String, String> customMap = new ConcurrentHashMap<>();

        public CustomLoader(Map<String, String> customMap) {
            CustomLoader.customMap = customMap;
        }

        // TODO 方式一
        public static <T> CustomLoader<T> getCustomLoader(Class<T> clazz) throws IOException {
            // 获取 class 类路径
            String classPath = clazz.getName();
            // 获取相应的资源
            ClassPathResource resource = new ClassPathResource("META-INF/custom/" + classPath);
            InputStream inputStream = resource.getInputStream();
            // 构造 Properties 对象
            Properties properties = new Properties();
            // 将指定的资源加载到 Properties 对象中
            properties.load(inputStream);
            Map<String, String> map = new HashMap<>();
            properties.forEach((proKey, proValue) -> {
                map.put((String) proKey, (String) proValue);
            });
            return new CustomLoader<>(map);
        }

        // TODO 方式二
        public static <T> CustomLoader<T> getCustomLoaderInputStream(Class<T> clazz) throws IOException {
            // 获取 class 类路径
            String classPath = clazz.getName();
            // 获取相应的资源
            ClassPathResource resource = new ClassPathResource("META-INF/custom/" + classPath);
            InputStream inputStream = resource.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            Map<String, String> map = new HashMap<>();
            if ((line = bufferedReader.readLine()) != null) {
                if (line.length() > 1) {
                    String[] split = line.split("=");
                    map.put(split[0].trim(), split[1].trim());
                }
            }
            return new CustomLoader<>(map);
        }

        public T getCustomClass(String className) {
            Object instance = null;
            if (customMap.containsKey(className)) {
                try {
                    // 加载指定的类，也就是说JVM会执行该类的静态代码段
                    Class<?> aClass = Class.forName(customMap.get(className));
                    // 获取该类的实例化对象
                    instance = aClass.getDeclaredConstructor().newInstance();
                } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException |
                         IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
            return (T) instance;
        }
    }
}
