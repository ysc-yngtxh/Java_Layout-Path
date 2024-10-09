package com.example;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SpringBootJasyptApplication implements ApplicationRunner{

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJasyptApplication.class, args);
    }


    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private StringEncryptor encryptor;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Environment environment = applicationContext.getBean(Environment.class);

        // 首先获取配置文件里的原始明文信息
        // 根据自己配置文件中的密码读取路径自行更改
        String oldPassword = environment.getProperty("custom.property.content");

        // 加密
        String encryptPassword = encryptor.encrypt( oldPassword );

        // 打印加密前后的结果对比
        System.out.println( "自定义内容原始明文为：" + oldPassword );
        System.out.println( "====================================" );
        System.out.println( "自定义内容原始明文加密后的结果为：" + encryptPassword );

    }
}
