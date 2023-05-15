package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色菜单关系表(SysRoleMenu)实体类
 *
 * @author makejava
 * @since 2023-05-15 17:01:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {
    @Serial
    private static final long serialVersionUID = -72023359821041803L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单id
     */
    private Long menuId;

}

