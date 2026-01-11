package com.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 驳回任务创建监听器
 * 负责初始化驳回处理任务、通知发起人、记录驳回原因
 */
@Slf4j
public class RejectTaskCreateListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        // 1. 获取任务基础信息
        String taskId = delegateTask.getId();
        String initiator = delegateTask.getAssignee(); // 发起人
        String processInstanceId = delegateTask.getProcessInstanceId();

        // 2. 日志记录
        log.info("驳回处理任务创建 - 任务ID：{}，发起人：{}，流程实例ID：{}",
                taskId, initiator, processInstanceId);

        // 3. 设置任务扩展属性
        delegateTask.setVariable("rejectHandleDeadline", System.currentTimeMillis() + 3 * 86400 * 1000); // 3天处理截止时间
        delegateTask.setVariable("rejectReason", delegateTask.getVariable("approvalRejectReason")); // 回显驳回原因

        // 4. 通知发起人（对接企业消息系统）
        notifyInitiator(delegateTask);
    }

    /**
     * 通知发起人任务已驳回
     */
    private void notifyInitiator(DelegateTask delegateTask) {
        String initiator = delegateTask.getAssignee();
        String rejectReason = (String) delegateTask.getVariable("approvalRejectReason");
        // 示例：推送消息给发起人（可替换为企业内部消息接口）
        log.info("已通知发起人【{}】：请假申请被驳回，驳回原因：{}，请及时处理（修改重提/终止）",
                initiator, rejectReason == null ? "未填写" : rejectReason);
    }

}
