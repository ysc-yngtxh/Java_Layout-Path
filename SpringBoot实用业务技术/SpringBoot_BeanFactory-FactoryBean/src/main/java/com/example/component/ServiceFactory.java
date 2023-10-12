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
// 实现接口 FactoryBean，重写方法
public class ServiceFactory implements FactoryBean<TestService>, ApplicationContextAware {

    /*
     FactoryBean是一个接口，当在IOC容器中的Bean实现了FactoryBean后，通过getBean(String BeanName)获取到的Bean对象
     并不是FactoryBean的实现类对象，而是这个实现类中的getObject()方法返回的对象。要想获取FactoryBean的实现类，
     就要getBean(&BeanName)，在BeanName之前加上&。
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
        return (TestService) (switchTurnOn ? applicationContext.getBean(STABLE) : applicationContext.getBean(ENHANCE));
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
