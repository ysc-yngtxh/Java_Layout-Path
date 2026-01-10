package com.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.api.runtime.shared.identity.UserGroupManager;
import org.activiti.core.common.spring.identity.ActivitiUserGroupManagerImpl;
import org.activiti.core.common.spring.identity.config.ActivitiSpringIdentityAutoConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

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
        try {
            String bpmnFilePath = "processes/borrowArchive.bpmn20.xml"; // 静态流程文件路径
            // 检查流程是否已经部署，避免重复部署
            long count = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionKey("borrowArchive")
                    .count();

            if (count == 0) {
                // 如果不存在，就开始部署流程，传入名称与文件路径
                Deployment deployment = repositoryService.createDeployment()
                        .addClasspathResource(bpmnFilePath) // 流程文件路径
                        .name("档案借阅流程") // 流程名
                        .deploy();
                log.info("Static Deployment Completed: Process Deployment ID: {}", deployment.getId());
            } else {
                log.info("borrowArchive Process is already deployed.");
            }
        } catch (Exception e) {
            log.error("Failed to deploy process", e);
        }
    }

}
