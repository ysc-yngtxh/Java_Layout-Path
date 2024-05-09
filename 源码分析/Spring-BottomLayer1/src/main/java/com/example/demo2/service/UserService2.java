package com.example.demo2.service;

import com.example.spring.annatation.Component;
import com.example.spring.interfaces.BeanNameAware;
import com.example.spring.interfaces.InitializingBean;
import com.example.spring.annatation.Autowired;
import com.example.spring.annatation.Scope;
import lombok.Setter;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-08 20:25
 * @apiNote TODO
 */
@Component("userService2")
@Scope("protoType")
public class UserService2 implements BeanNameAware, InitializingBean {

    @Autowired
    private OrderService orderService;

    @Setter
    private String name;

    public void test() {
        System.out.println("orderService = " + orderService);
        // 比如说我们希望在当前类中获取到 Bean 的名称并打印。那么可以通过 Aware 回调实现
        System.out.println("beanName = " + name);
    }

    @Override
    public void setBeanName(String beanName) {
        this.name = beanName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化～");
    }
}
