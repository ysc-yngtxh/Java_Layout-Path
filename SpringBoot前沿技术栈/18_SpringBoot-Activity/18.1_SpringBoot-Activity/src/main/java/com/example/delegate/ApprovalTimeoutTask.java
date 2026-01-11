package com.example.delegate;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * 审批超时提醒执行类
 * 定时器触发后执行（发送消息/邮件/短信提醒）
 */
@Slf4j
public class ApprovalTimeoutTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        // 1. 获取流程/任务信息
        String processInstanceId = execution.getProcessInstanceId();
        String taskType = (String) execution.getVariable("taskType");
        String approver = (String) execution.getVariable(taskType.equals("FIRST_LEVEL_APPROVAL") ? "approverA" : "approverB");

        // 2. 执行超时提醒（对接企业消息系统）
        sendTimeoutRemind(approver, taskType, processInstanceId);

        // 3. 更新提醒状态
        execution.setVariable("remindStatus", "SENT");
        log.info("超时提醒已发送 - 流程实例ID：{}，审批类型：{}，审批人：{}", processInstanceId, taskType, approver);
    }

    /**
     * 发送超时提醒（示例：可替换为邮件/短信/企业微信接口）
     */
    private void sendTimeoutRemind(String approver, String taskType, String processInstanceId) {
        String taskName = taskType.equals("FIRST_LEVEL_APPROVAL") ? "一级审批（直属领导）" : "二级审批（部门总监/HR）";
        String remindContent = String.format("【请假审批超时提醒】您有一条%s任务（流程ID：%s）已超过24小时未处理，请及时审批！",
                taskName, processInstanceId);
        // 调用企业消息推送接口
        // MessagePushService.push(approver, remindContent);
        log.info("向审批人【{}】发送超时提醒：{}", approver, remindContent);
    }

}
