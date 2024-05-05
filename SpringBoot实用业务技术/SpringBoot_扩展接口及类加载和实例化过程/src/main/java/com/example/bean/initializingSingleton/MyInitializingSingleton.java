package com.example.bean.initializingSingleton;

import com.example.bean.initializingBean.AppInitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-21 上午10:46
 * @apiNote TODO 在Spring容器对所有单例Bean初始化完成后执行一些自定义的初始化逻辑
 */
@Component
public class MyInitializingSingleton implements SmartInitializingSingleton {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterSingletonsInstantiated() {
        // 在所有单例Bean初始化完成后执行一些自定义的初始化逻辑 [即：在Bean定义的的init-method方法后执行]
        System.out.println("MyInitializingSingleton afterSingletonsInstantiated");

        // 通过注入的上下文获取Bean，并在该Bean初始化完成之后，去执行普通方法。
        AppInitializingBean appInitializingBean = applicationContext.getBean("appInitializingBean", AppInitializingBean.class);
        appInitializingBean.init22();
    }
}
