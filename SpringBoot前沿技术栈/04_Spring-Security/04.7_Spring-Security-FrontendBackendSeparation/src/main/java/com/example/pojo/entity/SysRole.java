package com.example.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色表(SysRole)表实体类
 *
 * @author makejava
 * @since 2023-05-13 16:49:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
public class SysRole implements Serializable {
    @Serial
    private static final long serialVersionUID = -3719211867127818113L;

    @TableId
    private Long id;

    // 角色名称
    private String name;

    // 角色权限字符串
    private String roleKey;

    // 角色状态 (0正常 1停用)
    private String status;

    // 是否删除(0未删除 1已删除)
    private Integer delFlag;

    // 创建人
    private Long createBy;

    // 创建时间
    private Date createTime;

    // 更新人
    private Long updateBy;

    // 更新时间
    private Date updateTime;

    // 备注
    private String remark;
}
