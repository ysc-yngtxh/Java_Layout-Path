package com.example.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ManagerApprovalDelegate implements JavaDelegate {
    
    @Override
    public void execute(DelegateExecution execution) {
        // 经理审批逻辑
        System.out.println("经理审批流程...");
        // 设置流程变量等
        execution.setVariable("managerApproved", true);
    }
}
