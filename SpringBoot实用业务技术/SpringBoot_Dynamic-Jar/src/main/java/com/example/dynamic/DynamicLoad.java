package com.example.dynamic;

import com.example.annotation.XxlJobCron;
import com.example.load.MyClassLoader;
import com.example.utils.IsSpringAnnotationUtils;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author 游家纨绔
 * @date 2024/02/22 17:54
 **/
@Component
public class DynamicLoad {

    // 动态加载
    // 由于此项目使用spring框架，以及xxl-job任务的机制调用动态加载的代码，因此要完成以下内容
    //
    // 将动态加载的jar包读到内存中
    // 将有spring注解的类，通过注解扫描的方式，扫描并手动添加到spring容器中。
    // 将@XxlJob注解的方法，通过注解扫描的方式，手动添加到xxljob执行器中。

    private static Logger logger = LoggerFactory.getLogger(DynamicLoad.class);

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, MyClassLoader> myClassLoaderCenter = new ConcurrentHashMap<>();

    @Value("${dynamicLoad.path}")
    private String path;

    /**
     * 动态加载指定路径下指定jar包
     * @param path
     * @param fileName
     * @param isRegistXxlJob  是否需要注册xxljob执行器，项目首次启动不需要注册执行器
     * @return map<jobHandler, Cron> 创建xxljob任务时需要的参数配置
     */
    public void loadJar(String path, String fileName, Boolean isRegistXxlJob) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        File file = new File(path +"/" + fileName);
        Map<String, String> jobPar = new HashMap<>();
        // 获取beanFactory
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        // 获取当前项目的执行器
        try {
            // URLClassloader加载jar包规范必须这么写
            URL url = new URL("jar:file:" + file.getAbsolutePath() + "!/");
            URLConnection urlConnection = url.openConnection();
            JarURLConnection jarURLConnection = (JarURLConnection)urlConnection;
            // 获取jar文件
            JarFile jarFile = jarURLConnection.getJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();

            // 创建自定义类加载器，并加到map中方便管理
            MyClassLoader myClassloader = new MyClassLoader(new URL[] { url }, ClassLoader.getSystemClassLoader());
            myClassLoaderCenter.put(fileName, myClassloader);
            Set<Class> initBeanClass = new HashSet<>(jarFile.size());
            // 遍历文件
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                if (jarEntry.getName().endsWith(".class")) {
                    // 1. 加载类到jvm中
                    // 获取类的全路径名
                    String className = jarEntry.getName().replace('/', '.').substring(0, jarEntry.getName().length() - 6);
                    // 1.1进行反射获取
                    myClassloader.loadClass(className);
                }
            }
            Map<String, Class<?>> loadedClasses = myClassloader.getLoadedClasses();
            XxlJobSpringExecutor xxlJobExecutor = new XxlJobSpringExecutor();
            for(Map.Entry<String, Class<?>> entry : loadedClasses.entrySet()){
                String className = entry.getKey();
                Class<?> clazz = entry.getValue();
                // 2. 将有@spring注解的类交给spring管理
                // 2.1 判断是否注入spring
                Boolean flag = IsSpringAnnotationUtils.hasSpringAnnotation(clazz);
                if(flag){
                    // 2.2交给spring管理
                    BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
                    AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
                    // 此处beanName使用全路径名是为了防止beanName重复
                    String packageName = className.substring(0, className.lastIndexOf(".") + 1);
                    String beanName = className.substring(className.lastIndexOf(".") + 1);
                    beanName = packageName + beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                    // 2.3注册到spring的beanFactory中
                    beanFactory.registerBeanDefinition(beanName, beanDefinition);
                    // 2.4允许注入和反向注入
                    beanFactory.autowireBean(clazz);
                    beanFactory.initializeBean(clazz, beanName);
                    /*if(Arrays.stream(clazz.getInterfaces()).collect(Collectors.toSet()).contains(InitializingBean.class)){
                        initBeanClass.add(clazz);
                    }*/
                    initBeanClass.add(clazz);
                }

                // 3. 带有XxlJob注解的方法注册任务
                // 3.1 过滤方法
                Map<Method, XxlJob> annotatedMethods = null;
                try {
                    annotatedMethods = MethodIntrospector.selectMethods(clazz,
                            new MethodIntrospector.MetadataLookup<XxlJob>() {
                                @Override
                                public XxlJob inspect(Method method) {
                                    return AnnotatedElementUtils.findMergedAnnotation(method, XxlJob.class);
                                }
                            });
                } catch (Throwable ex) {
                }
                // 3.2 生成并注册方法的JobHander
                for (Map.Entry<Method, XxlJob> methodXxlJobEntry : annotatedMethods.entrySet()) {
                    Method executeMethod = methodXxlJobEntry.getKey();
                    // 获取jobHandler和Cron
                    XxlJobCron xxlJobCron = executeMethod.getAnnotation(XxlJobCron.class);
                    if(xxlJobCron == null){
                        throw new CustomException("500", executeMethod.getName() + "()，没有添加@XxlJobCron注解配置定时策略");
                    }
                    if (!CronExpression.isValidExpression(xxlJobCron.value())) {
                        throw new CustomException("500", executeMethod.getName() + "(),@XxlJobCron参数内容错误");
                    }
                    XxlJob xxlJob = methodXxlJobEntry.getValue();
                    jobPar.put(xxlJob.value(), xxlJobCron.value());
                    if (isRegistXxlJob) {
                        executeMethod.setAccessible(true);
                        // regist
                        Method initMethod = null;
                        Method destroyMethod = null;
                        xxlJobExecutor.registJobHandler(xxlJob.value(), new CustomerMethodJobHandler(clazz, executeMethod, initMethod, destroyMethod));
                    }
                }

            }
            // spring bean实际注册
            initBeanClass.forEach(beanFactory::getBean);
        } catch (IOException e) {
            logger.error("读取{} 文件异常", fileName);
            e.printStackTrace();
            throw new RuntimeException("读取jar文件异常: " + fileName);
        }
    }
}