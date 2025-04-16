package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.entity.SysMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    @Select("""
            SELECT menu.id, menu.menu_name, menu.path, menu.component, menu.perms
            FROM `sys_menu` AS menu
            INNER JOIN `sys_role_menu` AS rm ON menu.id = rm.menu_id
            INNER JOIN `sys_role` AS role ON rm.role_id = role.id
            WHERE role.name = #{roleName}
            """)
    List<SysMenu> findRoleByMenu(String roleName);
}
