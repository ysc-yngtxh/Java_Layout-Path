package com.example;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

@SpringBootTest
class SpringBootActivityApplicationTests {

    @Autowired
    private RepositoryService repositoryService;


    @Test
    public Deployment deploy() {
        // 获取 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 进行部署
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/leave.bpmn20.xml")
                .name("请假流程")
                .deploy();

        System.out.println("流程部署id:" + deploy.getId());
        System.out.println("流程部署name:" + deploy.getName());
        return deploy;
    }


    @Test
    public Deployment deployZip() {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("bpmn/leave.bpmn20.zip");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        Deployment deploy = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("请假流程-zip")
                .deploy();
        System.out.println("流程部署id:" + deploy.getId());
        System.out.println("流程部署name:" + deploy.getName());
        return deploy;
    }


    // 查询流程部署列表
    @Test
    public void getDeploymentList() {
        List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
        if (!deployments.isEmpty()) {
            deployments.forEach(deployment -> {
                System.out.println("Id：" + deployment.getId());
                System.out.println("Name：" + deployment.getName());
                System.out.println("DeploymentTime：" + deployment.getDeploymentTime());
                System.out.println("Key：" + deployment.getKey());
            });
        }
    }

    // 查询流程定义列表
    @Test
    public void getProcessDefinition() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("流程定义key：" + processDefinition.getKey());
            System.out.println("流程定义name：" + processDefinition.getName());
            System.out.println("流程部署id：" + processDefinition.getDeploymentId());
        }
    }

    // 删除流程部署
    @Test
    public void deleteDeployment() {
        String deploymentId = "0f1cf913-0c4c-11f0-88fe-002b672ee8ea";
        // 级联删除流程部署，关联的流程实例、任务等都会被删除。第二个参数为false表示不级联删除
        repositoryService.deleteDeployment(deploymentId, false);
    }


    // TODO act_ru_task表中的SUSPENSION_STATE_字段发生变更，1代表激活、2代表挂起。
    // 流程实例的挂起
    public void suspendProcessInstance() {
        String processInstanceId = "cd81c512-0c56-11f0-828a-002b672ee8ea";
        runtimeService.suspendProcessInstanceById(processInstanceId);
    }

    // 流程实例的激活
    public void activeProcessInstance() {
        String processInstanceId = "cd81c512-0c56-11f0-828a-002b672ee8ea";
        runtimeService.activateProcessInstanceById(processInstanceId);
    }

    // 删除流程实例
    public void deleteProcessInstance() {
        String processInstanceId = "cd81c512-0c56-11f0-828a-002b672ee8ea";
        String deleteReason = "不想请假了";
        runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
    }
}
