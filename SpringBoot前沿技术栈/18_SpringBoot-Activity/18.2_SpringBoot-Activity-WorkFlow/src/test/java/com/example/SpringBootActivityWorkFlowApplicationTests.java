package com.example;

import java.util.List;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootActivityWorkFlowApplicationTests {

	@Test
	void testDeploy() {
		// 创建 ProcessEngine对象
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		// 获取 RepositoryService对象
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 进行部署
		Deployment deployment = repositoryService.createDeployment()
		                                         .addClasspathResource("processes/approval.bpmn20.xml")
		                                         .name("请假流程")
		                                         .deploy();
		// 输出部署的一些信息
		System.out.println("流程部署ID：" + deployment.getId());
		System.out.println("流程部署名称：" + deployment.getName());
	}

	// 或者使用自动部署（推荐）
	@Test
	public void testProcessDefinition() {
        // 获取 ProcessEngine 流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();

		List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
		processDefinitions.forEach(pd -> {
			System.out.println("流程定义: " + pd.getName() + " - " + pd.getKey());
		});
	}
}
