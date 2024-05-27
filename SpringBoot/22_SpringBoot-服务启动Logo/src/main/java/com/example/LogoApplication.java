package com.example;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 游家纨绔
 */
@SpringBootApplication
public class LogoApplication {

    public static void main(String[] args) {

        // 原来的springboot的大Logo
        // SpringApplication.run(LogoApplication.class,args);

        // 关闭springboot的大Logo
        // 获取入口
        SpringApplication springApplication = new SpringApplication(LogoApplication.class);
        // 设置它的属性
        springApplication.setBannerMode(Banner.Mode.CONSOLE); // Banner.Mode.OFF表示不要logo
        springApplication.run(args);


        // 修改Logo
        // SpringApplication.run(LogoApplication.class, args);
        // 在resources包下创建一个banner.txt文件，然后将你的Logo放入这个文件中，随后启动就OK

        // 修改Logo的网址：http://patorjk.com/software/taag/  和  https://www.bootschool.net/ascii
    }

}
