package com.example.dto;

import lombok.Data;

/**
 * 请假审批流程参数DTO
 */
@Data
public class LeaveApprovalDto {
    /** 提议人账号 */
    private String proposer;
    /** 第一审批人账号 */
    private String approverA;
    /** 第二审批人账号 */
    private String approverB;
    /** A审批结果：approved(通过)/rejected(驳回) */
    private String approvalResultA;
    /** B审批结果：approved(通过)/rejected(驳回) */
    private String approvalResultB;
    /** 请假申请ID（业务唯一标识） */
    private String applyId;
    /** 请假天数（扩展字段，可根据业务添加） */
    private Integer leaveDays;
    /** 请假原因（扩展字段） */
    private String leaveReason;
}
