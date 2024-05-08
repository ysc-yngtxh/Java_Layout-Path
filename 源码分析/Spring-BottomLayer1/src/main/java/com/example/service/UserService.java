package com.example.service;

import com.spring.annatation.Autowired;
import com.spring.interfaces.BeanNameAware;
import com.spring.annatation.Component;
import com.spring.interfaces.InitializingBean;
import com.spring.annatation.Scope;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-08 20:25
 * @apiNote TODO
 */
@Component("userService")
@Scope("protoType")
public class UserService implements BeanNameAware, InitializingBean {

    @Autowired
    private OrderService orderService;

    private String beanName;

    public void test() {
        System.out.println("orderService = " + orderService);
        // 比如说我们希望在当前类中获取到 Bean 的名称并打印。那么可以通过Aware 回调实现
        System.out.println("beanName = " + beanName);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化～");
    }
}
