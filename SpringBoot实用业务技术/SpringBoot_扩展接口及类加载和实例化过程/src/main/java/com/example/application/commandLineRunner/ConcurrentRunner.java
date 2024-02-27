package com.example.application.commandLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-07 18:20
 * @apiNote TODO
 */
@Component
public class ConcurrentRunner implements CommandLineRunner {
    /*
      这个接口也只有一个方法：run(String… args)，触发时机为整个项目启动完毕后，自动执行。
                          如果有多个CommandLineRunner，可以利用@Order来进行排序。
      使用场景：用户扩展此接口，进行启动项目之后一些业务的预处理。
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("项目启动完毕后，自动执行CommandLineRunner");
    }
}
