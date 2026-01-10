package com.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
// Activiti 默认集成 Spring Security 进行权限管理，如果不需要权限管理功能，可以通过 exclude 属性排除掉相关的自动配置类
@SpringBootApplication
public class SpringBootActivityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActivityApplication.class, args);
    }


    // 通过 RepositoryService 来部署流程文件
    @Autowired
    private RepositoryService repositoryService;

    @Override
    public void run(String... args) throws Exception {
        repositoryService.createDeployment()
                .name("请假审批流程")
                .addClasspathResource("processes/borrowArchive.bpmn20.xml")
                .deploy();
        System.out.println("流程部署成功！");
    }

}
