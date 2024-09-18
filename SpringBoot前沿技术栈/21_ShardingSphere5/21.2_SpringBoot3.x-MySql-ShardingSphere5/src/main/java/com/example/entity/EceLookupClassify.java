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
 * 字典表(EceLookupClassify)实体类
 *
 * @author 游家纨绔
 * @since 2024-09-19 00:58:28
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EceLookupClassify implements Serializable {
    @Serial
    private static final long serialVersionUID = 687760483410347788L;

    /**
     * 字典主键
     */
    private Long lookupId;
    /**
     * 字典名称
     */
    private String lookupName;
    /**
     * 字典类型
     */
    private String lookupType;
    /**
     * 状态：(0正常 1停用)
     */
    private Integer status;
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

