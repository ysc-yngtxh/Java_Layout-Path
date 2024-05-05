package com.example.自定义注解.utils;

import com.example.自定义注解.annotation.LoginRequired;
import org.reflections.Reflections;
import org.reflections.scanners.MethodParameterNamesScanner;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Filter;
import java.util.regex.Pattern;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: 获取标注了@LogRequired注解的所有Url：白名单列表
 * @date 2022/12/1 19:59
 */
public class WhiteListUri {


    // TODO Reflections依赖包中的实例详解
    public static void  getKnowledgePoints(){
        // 扫包
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages("com.boothsun.reflections") // 指定路径URL
                .addScanners(Scanners.SubTypes)          // 添加子类扫描工具
                .addScanners(Scanners.FieldsAnnotated)   // 添加 属性注解扫描工具
                .addScanners(Scanners.MethodsAnnotated)  // 添加 方法注解扫描工具
                .addScanners(Scanners.MethodsParameter)  // 添加方法参数扫描工具
        );

        // 反射出子类
        Set<Class<? extends Filter>> set = reflections.getSubTypesOf( Filter.class ) ;
        System.out.println("getSubTypesOf:" + set);

        // 反射出带有指定注解的类
        Set<Class<?>> ss = reflections.getTypesAnnotatedWith( LoginRequired.class );
        System.out.println("getTypesAnnotatedWith:" + ss);

        // 获取带有特定注解对应的方法
        Set<Method> methods = reflections.getMethodsAnnotatedWith( LoginRequired.class ) ;
        System.out.println("getMethodsAnnotatedWith:" + methods);

        // 获取带有特定注解对应的字段
        Set<Field> fields = reflections.getFieldsAnnotatedWith( LoginRequired.class ) ;
        System.out.println("getFieldsAnnotatedWith:" + fields);

        // 获取特定参数对应的方法
        Set<Method> someMethods = reflections.getMethodsWithParameter(long.class);// long类型参数的方法
        System.out.println("getMethodsMatchParams:" + someMethods);

        Set<Method> voidMethods = reflections.getMethodsReturn(void.class);// 无返回值的方法
        System.out.println( "getMethodsReturn:" + voidMethods);

        // 获取资源文件
        Set<String> properties = reflections.getResources(Pattern.compile(".*\\.properties"));
    }


    // TODO 获取自定义注解在Controller层上的路径名集合，进而处理是否白名单
    public static List<String> getWhiteUrls(String packageName) {

        // TODO 使用反射依赖包
        List<String> whiteUrls = new ArrayList<>();
        // 设置扫描的包的路径
        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage(packageName)) // 指定路径URL
                        .setScanners(new MethodParameterNamesScanner())   // 指定添加方法参数扫描工具
        );

        // 扫描指定包带有@LoginRequired注解的方法集合
        Set<Method> methodsAnnotatedWith = reflections.getMethodsAnnotatedWith(LoginRequired.class);
        if (methodsAnnotatedWith != null && !methodsAnnotatedWith.isEmpty()) {
            for (Method method : methodsAnnotatedWith) {
                // 获取到接口方法上注解值
                String[] excludeUrl = method.getAnnotation(RequestMapping.class).value();
                // 获取到接口方法声明类上的注解值
                String[] valueArr = method.getDeclaringClass().getAnnotation(RequestMapping.class).value();
                String baseUrl = "";
                if (valueArr.length > 0) {
                    baseUrl = valueArr[0];
                }
                String resultUrl = baseUrl + excludeUrl[0];
                whiteUrls.add(resultUrl);
            }
        }
        return whiteUrls;
    }
}
