package com.spring;

import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-07 23:15
 * @apiNote TODO Spring容器
 */
public class YoujiawankuConfigApplicationContext {

    private Class rootClass;

    @SneakyThrows
    public YoujiawankuConfigApplicationContext(Class rootClass) {
        this.rootClass = rootClass;

        // 流程：解析配置类 [ ComponentScan注解 -----> 扫描路径 -----> 扫描 ]

        // TODO 1、通过获取 @ComponentScan 注解，获取扫描路径

        // 获取配置类上的 @ComponentScan 注解，从而获取扫描路径
        ComponentScan componentScan = (ComponentScan) rootClass.getDeclaredAnnotation(ComponentScan.class);
        String scanPath = componentScan.value();

        // TODO 2、扫描路径下的所有类

        // 获取当前类加载器
        ClassLoader classLoader = YoujiawankuConfigApplicationContext.class.getClassLoader();
        // 通过类加载器获取扫描路径的下的资源 URL (绝对地址)
        URL url = classLoader.getResource(scanPath.replace(".", "/"));
        // 将 URL 进行中文解码 (避免路径中存在中文乱码)
        String urlDecodePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8.name());

        // 创建 File 对象
        File file = new File(urlDecodePath);
        // 判断该地址下的文件是否为目录
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                // 获取文件的绝对路径
                String fileName = f.getPath();
                // 筛选出文件的相对路径
                String classPath = fileName
                        .substring(fileName.indexOf("com"), fileName.indexOf(".class"))
                        .replace("/", ".");
                // 加载相对路径的类文件
                Class<?> aClass = classLoader.loadClass(classPath);
                // 判断该类上是否存在 @Component 注解
                if (aClass.isAnnotationPresent(Component.class)) {

                }
            }
        }


        // 解析类上的注解
    }

    public Object getBean(String beanName) {

        return null;
    }
}
