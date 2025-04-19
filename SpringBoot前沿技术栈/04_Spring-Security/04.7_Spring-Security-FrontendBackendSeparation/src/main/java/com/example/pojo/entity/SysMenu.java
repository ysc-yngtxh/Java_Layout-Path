package com.example.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * sys_menu
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class SysMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 菜单可见性(0显示 1隐藏)
     */
    private String visible;

    /**
     * 菜单状态 (0正常 1停用)
     */
    private String status;

    /**
     * 权限标识
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<String> permission;

    /**
     * 菜单图标
     */
    private byte[] icon;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除(0未删除 1已删除)
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;
}
