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
        // 1. 获取动态参数
        String processInstanceId = execution.getProcessInstanceId();
        String taskType = (String) execution.getVariable("taskType");
        String currentApprover = (String) execution.getVariable("currentApprover");

        // 2. 区分审批级别执行超时提醒（对接企业消息系统）
        if ("FIRST_LEVEL_APPROVAL".equals(taskType)) {
            sendTimeoutRemind(processInstanceId, currentApprover, "一级（直属领导）审批超时");
        } else if ("SECOND_LEVEL_APPROVAL".equals(taskType)) {
            sendTimeoutRemind(processInstanceId, currentApprover, "二级（部门总监/HR）审批超时");
        }

        // 3. 更新提醒状态
        execution.setVariable("remindStatus", "SENT");
        log.info("超时提醒已发送 - 流程实例ID：{}，审批类型：{}，审批人：{}", processInstanceId, taskType, currentApprover);
    }

    /**
     * 发送超时提醒（示例：可替换为邮件/短信/企业微信接口）
     */
    private void sendTimeoutRemind(String processInstanceId, String approver, String title) {
        String remindContent = String.format(
                "【请假审批超时提醒】您有一条%s任务（流程ID：%s）已超过24小时未处理，请及时审批！",
                title,
                processInstanceId
        );
        // 调用企业消息推送接口
        // MessagePushService.push(approver, remindContent);
        log.info("向审批人【{}】发送超时提醒：{}", approver, remindContent);
    }

}
