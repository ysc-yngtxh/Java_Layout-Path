package com.example;

import com.example.domain.SysUser;
import com.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {

    public static void main(String[] args) {
        //从spring容器中获取UserService类型的对象，dao,service都是spring管理的
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService service = (UserService) ctx.getBean("userService");

        SysUser user = new SysUser();
        user.setName("lisi");
        user.setAge(23);
        service.addUser(user);
    }

}
