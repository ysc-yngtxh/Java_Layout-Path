package com.example.service.impl;

import com.example.dto.TaskDto;
import com.example.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 流程管理接口实现（核心业务）
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    // Activiti核心服务：流程部署、查询流程定义
    private final RepositoryService repositoryService;
    // Activiti核心服务：流程实例管理
    private final RuntimeService runtimeService;
    // Activiti核心服务：任务管理
    private final TaskService taskService;


    /**
     * 1. 部署流程（可选：Activiti会自动部署processes目录下的bpmn文件）
     */
    public void deployProcess() {
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
                        .name("请假流程") // 流程名
                        .deploy();
                log.info("Static Deployment Completed: Process Deployment ID: {}", deployment.getId());
            } else {
                log.info("borrowArchive Process is already deployed.");
            }
        } catch (Exception e) {
            log.error("Failed to deploy process", e);
        }
    }

    /**
     * 2. 启动流程实例（发起请假，指定审批人）
     * @param processKey 请假单ID（业务关联）
     * @param variables 审批参数（比如：{"applyUser":"Make","approver":"游家纨绔","leaveDays":3,"leaveReason":"事假"}）
     * @return 流程实例ID
     */
    @Override
    public ProcessInstance startProcess(String processKey, Map<String, Object> variables) {
        // 启动流程实例：构造器参数说明
        //     processDefinitionKey：流程定义的key，即bpmn文件中定义的id属性值。
        //     businessKey：业务key，将流程实例和业务数据关联起来。
        //     variables：动态传入的参数，可作为后续分支流转的条件。
        return runtimeService.startProcessInstanceByKey(processKey, variables);
    }

    /**
     * 3. 查询执行人的待办审批任务
     * @param assignee 执行人（比如：zhangsan）
     * @return 待办任务列表
     */
    @Override
    public List<TaskDto> getTodoTaskList(String assignee) {
        // 查询指定负责人的待办任务
        List<Task> taskList = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .orderByTaskCreateTime()
                .desc()
                .list();
        return taskList.stream().map(TaskDto::new).toList();
    }

    /**
     * 4. 执行人完成审批（核心：审批通过/驳回）
     * @param taskId 待办任务ID（从待办列表中获取）
     * @param variables 审批参数（比如：{"applyUser":"游家纨绔","approveResult":"approved","approveRemark":"同意请假"}）
     */
    @Override
    public void completeTask(String taskId, Map<String, Object> variables) {
        // 完成指定任务 + 传入审批参数
        taskService.complete(taskId, variables);
    }


    /**
     * 3. 查询流程实例
     */
    public void getProcessInstance() {
        // 通过流程定义key查询
        String processDefinitionKey = "leave";
        // runtimeService.createProcessInstanceQuery() 方法用于创建一个流程实例查询对象，
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey).list();
        for (ProcessInstance processInstance : list) {
            System.out.println(processInstance.getProcessInstanceId());
        }

        // 通过业务key查询
        String businessKey = "zl-leave";
        List<ProcessInstance> list1 = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey).list();
        for (ProcessInstance processInstance : list1) {
            System.out.println(processInstance.getProcessInstanceId());
        }
    }
}
