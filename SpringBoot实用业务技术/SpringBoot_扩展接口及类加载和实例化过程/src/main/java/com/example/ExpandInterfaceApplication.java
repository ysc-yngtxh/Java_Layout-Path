package com.example;

import com.example.application.aplicationContextInitializer.MyApplicationContextInitializer;
import com.example.bean.beanPostProcessor.MyBeanPostProcess;
import com.example.excutor.Mouse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpandInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ExpandInterfaceApplication.class);
        // ApplicationContextInitializer接口扩展生效配置
        springApplication.addInitializers(new MyApplicationContextInitializer());
        springApplication.run(args);
        // 初始化Mouse
        Mouse mouse = new Mouse();
        MyBeanPostProcess myBeanPostProcess = new MyBeanPostProcess();
    }
}
