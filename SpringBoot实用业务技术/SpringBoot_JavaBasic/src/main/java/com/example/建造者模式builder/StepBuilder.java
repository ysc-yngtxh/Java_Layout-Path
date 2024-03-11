package com.example.建造者模式builder;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.vo.User;
import lombok.SneakyThrows;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/29 23:03
 */
public class StepBuilder {
    public static final Log log = LogFactory.get(StepBuilder.class);

    @SneakyThrows
    public static void main(String[] args) {
        // 使用step builder形成固定样式的链式编程
        User build = UserBuilder.builder().withId(1L).withName("Ysc").withEmail("SDA").withAge(56).build();
        log.warn(build.toString());

        // 使用Lombok的@Builder注解，我们可以进行自定义样式的链式编程(其实我们也可以参照，自己编写)
        User student = User.builder().name("student").age(25).build();
        log.warn(student.toString());

        // 使用@SneakyThrows注解就可以不用在代码中书写try-catch,项目在编译时候会自动在.class文件里生成
        int t = 10/0;
    }
}
