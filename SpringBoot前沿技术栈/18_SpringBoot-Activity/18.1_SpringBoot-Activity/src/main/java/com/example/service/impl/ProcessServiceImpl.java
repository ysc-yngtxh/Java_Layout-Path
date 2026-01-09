package com.example.service.impl;

import com.example.service.ProcessService;
import lombok.RequiredArgsConstructor;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 流程管理接口实现（核心业务）
 */
@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    // Activiti核心服务：流程实例管理
    private final RuntimeService runtimeService;
    // Activiti核心服务：任务管理
    private final TaskService taskService;

    @Override
    public ProcessInstance startProcess(String processKey, Map<String, Object> variables) {
        // 启动流程实例：构造器参数说明
        //     processDefinitionKey：流程定义的key，即bpmn文件中定义的id属性值。
        //     businessKey：业务key，将流程实例和业务数据关联起来。
        //     variables：动态传入的参数，可作为后续分支流转的条件。
        return runtimeService.startProcessInstanceByKey(processKey, variables);
    }

    // 查询流程实例
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

    @Override
    public List<Task> getTodoTaskList(String assignee) {
        // 查询指定负责人的待办任务
        return taskService.createTaskQuery()
                .taskAssignee(assignee)
                .orderByTaskCreateTime()
                .desc()
                .list();
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> variables) {
        // 完成指定任务 + 传入审批参数
        taskService.complete(taskId, variables);
    }

}
