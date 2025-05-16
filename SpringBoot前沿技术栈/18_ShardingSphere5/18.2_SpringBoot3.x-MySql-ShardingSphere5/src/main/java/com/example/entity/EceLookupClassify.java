package com.example.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 字典类别表(EceLookupClassify)实体类
 *
 * @author 游家纨绔
 * @since 2024-09-20 21:10:00
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EceLookupClassify implements Serializable {
    @Serial
    private static final long serialVersionUID = 223883240660535570L;

    /**
     * 类别ID
     */
    private Long classifyId;
    /**
     * 类别编码
     */
    private String classifyCode;
    /**
     * 类别名称
     */
    private String classifyName;
    /**
     * 类别类型
     */
    private String classifyType;
    /**
     * 状态：(0正常 1停用)
     */
    private Integer status;
    /**
     * 类别描述
     */
    private String classifyDesc;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createdDate;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updatedDate;
    /**
     * 备注
     */
    private String remark;
}
