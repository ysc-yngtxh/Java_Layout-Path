package com.example.application.aplicationContextInitializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-27 15:08
 * @apiNote TODO 目前没有具体场景使用过
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    // ApplicationContextInitializer用于在应用程序上下文（ApplicationContext）创建之前对其进行自定义初始化。
    // 通过实现该接口，您可以在应用程序上下文启动之前执行一些额外的配置或准备工作。
    //
    // 实现ApplicationContextInitializer接口需要实现其唯一的方法initialize，该方法接受一个泛型参数，表示正在创建的应用程序上下文。
    // 在该方法中，您可以对应用程序上下文进行各种自定义操作，例如添加属性源、注册Bean定义、设置环境变量等。
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String str = applicationContext.getEnvironment().getProperty("user.dir");
        System.out.println(str);
        System.out.println("ApplicationContextInitializer实现类");
    }

    // 因为这时候Spring容器还没被初始化，所以想要自己的扩展的生效，有以下三种方式：
    // 1、在启动类中用springApplication.addInitializers(new TestApplicationContextInitializer())语句加入
    // 2、配置文件配置context.initializer.classes=com.example.demo.TestApplicationContextInitializer
    // 3、Spring SPI扩展，在spring.factories中加入org.springframework.context.ApplicationContextInitializer=com.example.MyApplicationContextInitializer
}
