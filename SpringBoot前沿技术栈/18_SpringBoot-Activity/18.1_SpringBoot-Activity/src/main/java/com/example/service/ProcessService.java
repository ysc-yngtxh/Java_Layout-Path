package com.example.service;

import com.example.dto.TaskDto;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * 流程管理核心接口
 */
public interface ProcessService {

    /**
     * 1. 部署流程（可选：Activiti会自动部署processes目录下的bpmn文件）
     */
     void deployProcess();

    /**
     * 2. 启动流程实例（发起请假申请）
     * @param processKey 流程定义ID（leaveProcess）
     * @param variables 流程参数（申请人ID、审批人ID、请假天数等）
     * @return 流程实例对象
     */
    ProcessInstance startProcess(String processKey, Map<String, Object> variables);

    /**
     * 3. 查询个人待办任务
     * @param assignee 任务负责人ID
     * @return 待办任务列表
     */
    List<TaskDto> getTodoTaskList(String assignee);

    /**
     * 4. 完成任务（审批通过/驳回）
     * @param taskId 任务ID
     * @param variables 审批参数
     */
    void completeTask(String taskId, Map<String, Object> variables);
}
