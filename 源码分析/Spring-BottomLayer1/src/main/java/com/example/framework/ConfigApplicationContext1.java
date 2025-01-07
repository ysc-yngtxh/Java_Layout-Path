package com.example.framework;

import com.example.framework.annotation.Component;
import com.example.framework.annotation.ComponentScan;
import com.example.framework.annotation.Scope;
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
public class ConfigApplicationContext1 {

    // 配置类
    private Class<?> rootClass;

    // 单例池
    private Map<String, Object> singletonBeanMap = new HashMap<>();
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public ConfigApplicationContext1(Class<?> rootClass) {
        this.rootClass = rootClass;

        // 流程：解析配置类
        // [ ComponentScan注解 ---> 扫描路径 ---> 扫描类文件 ---> BeanDefinition ---> BeanDefinitionMap]

        // 扫描
        scan(rootClass);

        // 实例化Bean，并注册到单例池中
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanDefinition); // 单例 Bean
                singletonBeanMap.put(entry.getKey(), bean);
            }
        }
    }

    @SneakyThrows
    private Object createBean(BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getClazz();
        return clazz.getDeclaredConstructor().newInstance();
    }

    @SneakyThrows
    private void scan(Class<?> rootClass) {
        // TODO 1、通过获取 @ComponentScan 注解，获取扫描路径
        ComponentScan componentScanAnnotation = rootClass.getDeclaredAnnotation(ComponentScan.class);
        String scanPath = componentScanAnnotation.value();

        // TODO 2、扫描路径下的所有类
        // 获取当前类加载器
        ClassLoader classLoader = ConfigApplicationContext1.class.getClassLoader();
        // 通过类加载器获取扫描路径的下的资源 URL (绝对地址)
        URL url = classLoader.getResource(scanPath.replace(".", "/"));
        // 将 URL 进行中文解码 (避免路径中存在中文乱码)
        String urlDecodePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);

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
                // ComponentScan的路径是包路径，这里的是文件路径。加载相对路径的类文件
                Class<?> aClass = classLoader.loadClass(classPath);

                // TODO 3、解析类上的注解，将类的相关元信息封装为 BeanDefinition 并存入 beanDefinitionMap 中
                // 判断该类上是否存在 @Component 注解
                if (aClass.isAnnotationPresent(Component.class)) {
                    // 判断该类为单例Bean 还是原型(protoType) Bean
                    // 1、作为一个单例Bean来说，我们在调用 getBean() 方法时可以直接从单例池中取出 Bean对象
                    // 2、作为一个原型Bean来说，每次调用 getBean() 方法都需要创建一个新的 Bean 对象
                    //    Spring 在创建一个新的 Bean 对象时，getBean(String beanName) 中的 beanName 具体是哪个类，无法得知。
                    //    小曹会说这个名称就是类名的首字母小写。那如果我在 @Component("自定义名称") Spring 咋办？
                    //    而且，就算没有自定义名称，那万一出现了不同包下同一个类名的文件咋办？Spring 应该获取哪个类的Bean？
                    //    由此，Spring 引出了一个 BeanDefinition 的概念

                    Component componentAnnotation = aClass.getDeclaredAnnotation(Component.class);
                    String beanName = componentAnnotation.value();
                    // BeanDefinition
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
