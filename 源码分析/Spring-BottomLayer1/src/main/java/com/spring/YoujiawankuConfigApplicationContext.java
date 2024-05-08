package com.spring;

import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-07 23:15
 * @apiNote TODO Spring容器
 */
public class YoujiawankuConfigApplicationContext {

    private Class rootClass;

    private Map<String, Object> singletonBeanMap = new HashMap<>();  // 单例池
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public YoujiawankuConfigApplicationContext(Class<?> rootClass) {
        this.rootClass = rootClass;

        // 流程：解析配置类
        // [ ComponentScan注解 ---> 扫描路径 ---> 扫描 ---> BeanDefinition ---> BeanDefinitionMap]

        scan(rootClass);

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanDefinition); // 单例 Bean
                singletonBeanMap.put(entry.getKey(), bean);
            }
        }

        // 解析类上的注解
    }

    @SneakyThrows
    private Object createBean(BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getClazz();
        return clazz.getDeclaredConstructor().newInstance();
    }

    @SneakyThrows
    private void scan(Class<?> rootClass) {
        // TODO 1、通过获取 @ComponentScan 注解，获取扫描路径

        // 获取配置类上的 @ComponentScan 注解，从而获取扫描路径
        ComponentScan componentScan = rootClass.getDeclaredAnnotation(ComponentScan.class);
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
            File[] files = file.listFiles();  // 获取 File 对象下的所有文件
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
                    // 判断该类为单例Bean 还是原型(protoType) Bean
                    // 1、如果作为一个单例Bean来说，我们在调用 getBean() 方法时可以直接从单例池中取出 Bean对象
                    // 2、如果作为一个原型Bean来说，每次调用 getBean() 方法都需要创建一个新的 Bean 对象
                    //    那么对于 Spring 来说，它不知道 getBean(String beanName) 中的 beanName 具体是哪个类。
                    //    小曹会说这个名称就是类名的首字母小写。那如果我在 @Component("自定义名称") Spring 咋办？
                    //    而且，就算小曹说的对，那万一出现了不同包下同一个类名的文件咋办？Spring 应该获取哪个类的Bean？
                    //    由此，Spring 引出了一个 BeanDefinition 的概念

                    Component componentAnnotation = aClass.getDeclaredAnnotation(Component.class);
                    String beanName = componentAnnotation.value();
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setClazz(aClass);
                    if (aClass.isAnnotationPresent(Scope.class)) {
                        Scope scopeAnnotation = aClass.getDeclaredAnnotation(Scope.class);
                        beanDefinition.setScope(scopeAnnotation.value());
                    } else {
                        // 获取 @Scope 注解属性 value 的默认值
                        String defaultValue = Scope.class.getDeclaredMethods()[0].getDefaultValue().toString();
                        beanDefinition.setScope(defaultValue);
                    }
                    beanDefinitionMap.put(beanName, beanDefinition);
                }
            }
        }
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            // 判断该 BeanDefinition 是否为单例 Bean
            if (beanDefinition.getScope().equals("singleton")) {
                return singletonBeanMap.get(beanName);
            } else {
                return createBean(beanDefinition);
            }
        } else {
            throw new NullPointerException("BeanDefinition 不存在, 请检查配置类是否正确");
        }
    }
}
