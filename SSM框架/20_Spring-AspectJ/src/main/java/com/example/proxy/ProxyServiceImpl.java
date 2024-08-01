package com.example.proxy;

import com.example.jdk.JdkService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-11 10:12
 * @apiNote TODO
 */
@Service(value = "proxyService")
public class ProxyServiceImpl implements ProxyService {

    // TODO 一旦代理对象创建完成，Spring会将其注册到容器中，替代原来的目标对象。
    //      这意味着，当其他Bean需要注入目标类的实例时，实际上注入的是代理对象。
    //      代理对象的target是指向目标对象的
    @Autowired
    private JdkService jdkService;
    public void saveRpc() {
        System.out.println("获取的是jdkService：" + jdkService.getClass().getName());
        String like = jdkService.like("123", 1);
        System.out.println("saveUser方法执行..." + like);
    }


    @Override
    public void saveUser() {
        System.out.println("saveUser方法执行...");
    }

    @Override
    public void saveSigUser() {
        System.out.println("saveSigUser方法执行...");
        // 直接调用saveUser()方法，这里使用的对象是this，也就是当前对象。
        // 而Aop是基于动态代理实现的，这里没有代理对象，因此对saveUser()方法的切面编程是不生效执行的
        this.saveUser();
    }

    @Override
    public void saveAllUser() {
        System.out.println("saveAllUser方法执行...");
        // AopContext.currentProxy() 是 Spring 框架中的一个静态方法，用于获取当前正在执行的代理对象。
        // 这个方法是在组件内部调用的，目的是让当前组件能够访问自己的代理对象，从而在方法内部调用目标方法或其他切入点。
        // 将 currentProxy 强制转换为 ProxyService 类型。这个时候saveUser()方法的切面编程是会生效执行
        ProxyService currentProxy = (ProxyService) AopContext.currentProxy();
        currentProxy.saveUser();
    }
}
