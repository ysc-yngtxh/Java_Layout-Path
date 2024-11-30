package com.example.pojo.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private String menu_name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 菜单状态(0显示 1隐藏)
     */
    private String visible;

    /**
     * 菜单状态 (0正常 1停用)
     */
    private String status;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 创建人
     */
    private Long create_by;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新人
     */
    private Long update_by;

    /**
     * 更新时间
     */
    private Date update_time;

    /**
     * 是否删除(0未删除 1已删除)
     */
    private Integer del_flag;

    /**
     * 备注
     */
    private String remark;

}