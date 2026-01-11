package com.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 审批任务创建监听器（一级/二级审批通用）
 * 负责初始化审批任务、设置超时提醒、记录创建日志
 */
@Slf4j
public class ApproveTaskCreateListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        // 1. 获取任务基础信息
        String taskId = delegateTask.getId();
        String taskName = delegateTask.getName();
        String processInstanceId = delegateTask.getProcessInstanceId();
        String assignee = delegateTask.getAssignee();

        // 2. 日志记录（便于问题排查）
        log.info("审批任务创建 - 任务ID：{}，任务名称：{}，流程实例ID：{}，审批人：{}",
                taskId, taskName, processInstanceId, assignee);

        // 3. 设置任务扩展属性（便于前端展示/后续超时处理）
        delegateTask.setVariable("taskCreateTime", System.currentTimeMillis());
        delegateTask.setVariable("timeoutRemindMinutes", 1440); // 24小时 = 1440分钟
        delegateTask.setVariable("remindStatus", "UN_SENT"); // 提醒状态：未发送

        // 4. 可选：初始化超时提醒（也可通过BPMN定时器事件实现，二选一）
        initTimeoutRemind(delegateTask);
    }

    /**
     * 初始化超时提醒（可对接消息推送/邮件/短信）
     */
    private void initTimeoutRemind(DelegateTask delegateTask) {
        String assignee = delegateTask.getAssignee();
        String taskName = delegateTask.getName();
        // 此处可对接企业内部消息系统，比如：
        //     1. 延迟24小时发送提醒（通过定时任务框架如 XXL-Job/Quartz）
        //     2. 或调用消息推送接口，标记超时提醒规则
        log.info("已为审批人【{}】的【{}】任务设置24小时超时提醒", assignee, taskName);
    }

}
