package com.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
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
                .addClasspathResource("processes/leaveFlow.bpmn20.xml")
                .deploy();
        System.out.println("流程部署成功！");
    }

}
