package com.example.component;

import com.example.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
// 实现接口 FactoryBean，初始化Bean对象 -- TestService，重写方法
public class ServiceFactory implements FactoryBean<TestService>, ApplicationContextAware {

    /* 创建Bean方式有：①XML文件的bean标签、②类上标注@Service...等注解、③Configuration配置类下的@Bean方式
     * FactoryBean是一个接口，该类也是SpringIOC容器是创建Bean的另一种形式，这种方式创建Bean会有加成方式，融合了简单的工厂设计模式于装饰器模式。
     * 当在Ioc容器中的Bean实现了FactoryBean后，通过 getBean(String BeanName) 获取到的Bean对象，并不是FactoryBean的实现类对象，而是这个实现类中的getObject()方法返回的对象。
     * 当想获取实现了FactoryBean接口的实现类，就要通过 getBean(&BeanName)，在BeanName之前加上&。
     *
     * FactoryBean的特殊之处在于它向容器中注册两个Bean：
     *             一个是实现了FactoryBean接口的当前类。
     *             一个是 FactoryBean.getObject() 方法返回值所代表的Bean。
     * 比如当前类中：注入了两个Bean（1）、ServiceFactory
     *                         （2）、TestService【这是个接口类，所以getObject()方法返回的是其实现类的实例】
     *
     * 方法：
     *      T getObject()：返回实例
     *      Class<?> getObjectType()：返回该装饰对象的Bean的类型
     *      default boolean isSingleton()：Bean是否为单例
     */

    private final static Logger log = LoggerFactory.getLogger(ServiceFactory.class);

    /**
     * 稳定版的bean name
     */
    private final String STABLE = "stableServiceImpl";

    /**
     * 增强版的bean name
     */
    private final String ENHANCE = "enhanceServiceImpl";

    /**
     * 应用程序上下文
     */
    private ApplicationContext applicationContext;

    /**
     * 配置中心的开关配置
     */
    @Value("${switch.isTurnOn}")
    private boolean switchTurnOn;

    @Override
    public TestService getObject() {
        return (TestService) (switchTurnOn
                ? applicationContext.getBean(STABLE)
                : applicationContext.getBean(ENHANCE)
        );
    }
    
    @Override
    public Class<?> getObjectType() {
        return TestService.class;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
