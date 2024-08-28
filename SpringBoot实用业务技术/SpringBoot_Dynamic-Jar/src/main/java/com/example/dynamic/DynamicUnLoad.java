package com.example.dynamic;

import com.example.load.MyClassLoader;
import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-22 15:52
 * @apiNote TODO 动态卸载
 */
public class DynamicUnLoad {

    private static Logger logger = LoggerFactory.getLogger(DynamicLoad.class);

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, MyClassLoader> myClassLoaderCenter = new ConcurrentHashMap<>();

    // 动态卸载的过程，就是将动态加载的代码，从内存，spring以及xxljob中移除。
    public void unloadJar(String fileName) throws IllegalAccessException, NoSuchFieldException {
        // 获取加载当前jar的类加载器
        MyClassLoader myClassLoader = myClassLoaderCenter.get(fileName);

        // 获取jobHandlerRepository私有属性,为了卸载xxljob任务
        Field privateField = XxlJobExecutor.class.getDeclaredField("jobHandlerRepository");
        // 设置私有属性可访问
        privateField.setAccessible(true);
        // 获取私有属性的值jobHandlerRepository
        XxlJobExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        Map<String, IJobHandler> jobHandlerRepository = (ConcurrentHashMap<String, IJobHandler>) privateField.get(xxlJobSpringExecutor);
        // 获取beanFactory，准备从spring中卸载
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        Map<String, Class<?>> loadedClasses = myClassLoader.getLoadedClasses();

        Set<String> beanNames = new HashSet<>();
        for (Map.Entry<String, Class<?>> entry: loadedClasses.entrySet()) {
            // 1. 将xxljob任务从xxljob执行器中移除
            // 1.1 截取beanName
            String key = entry.getKey();
            String packageName = key.substring(0, key.lastIndexOf(".") + 1);
            String beanName = key.substring(key.lastIndexOf(".") + 1);
            beanName = packageName + beanName.substring(0, 1).toLowerCase() + beanName.substring(1);

            // 获取bean，如果获取失败，表名这个类没有加到spring容器中，则跳出本次循环
            Object bean = null;
            try{
                bean = applicationContext.getBean(beanName);
            }catch (Exception e){
                // 异常说明spring中没有这个bean
                continue;
            }

            // 1.2 过滤方法
            Map<Method, XxlJob> annotatedMethods = null;
            try {
                annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
                        new MethodIntrospector.MetadataLookup<XxlJob>() {
                            @Override
                            public XxlJob inspect(Method method) {
                                return AnnotatedElementUtils.findMergedAnnotation(method, XxlJob.class);
                            }
                        });
            } catch (Throwable ex) {
            }
            // 1.3 将job从执行器中移除
            for (Map.Entry<Method, XxlJob> methodXxlJobEntry : annotatedMethods.entrySet()) {
                XxlJob xxlJob = methodXxlJobEntry.getValue();
                jobHandlerRepository.remove(xxlJob.value());
            }
            // 2.0从spring中移除，这里的移除是仅仅移除的bean，并未移除bean定义
            beanNames.add(beanName);
            beanFactory.destroyBean(beanName, bean);
        }
        // 移除bean定义
        Field mergedBeanDefinitions = beanFactory.getClass()
                .getSuperclass()
                .getSuperclass().getDeclaredField("mergedBeanDefinitions");
        mergedBeanDefinitions.setAccessible(true);
        Map<String, RootBeanDefinition> rootBeanDefinitionMap = ((Map<String, RootBeanDefinition>) mergedBeanDefinitions.get(beanFactory));
        for (String beanName : beanNames) {
            beanFactory.removeBeanDefinition(beanName);
            // 父类bean定义去除
            rootBeanDefinitionMap.remove(beanName);
        }

        // 卸载父任务，子任务已经在循环中卸载
        jobHandlerRepository.remove(fileName);
        // 3.2 从类加载中移除
        try {
            // 从类加载器底层的classes中移除连接
            Field field = ClassLoader.class.getDeclaredField("classes");
            field.setAccessible(true);
            Vector<Class<?>> classes = (Vector<Class<?>>) field.get(myClassLoader);
            classes.removeAllElements();
            // 移除类加载器的引用
            myClassLoaderCenter.remove(fileName);
            // 卸载类加载器
            myClassLoader.unload();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.error("动态卸载的类，从类加载器中卸载失败");
            e.printStackTrace();
        }
        logger.error("{} 动态卸载成功", fileName);

    }
}
