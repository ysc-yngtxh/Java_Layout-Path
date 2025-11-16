package com.example.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class HrApprovalDelegate implements JavaDelegate {
    
    @Override
    public void execute(DelegateExecution execution) {
        // 人事审批逻辑
        System.out.println("人事审批流程...");
        // 设置流程变量等
        execution.setVariable("hrApproved", true);
    }
}
