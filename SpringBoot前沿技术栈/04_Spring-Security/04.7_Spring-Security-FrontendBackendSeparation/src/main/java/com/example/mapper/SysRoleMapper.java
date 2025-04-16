package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-13 16:49:36
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    @Select("""
            SELECT role.id, role.name, role.role_key
            FROM `sys_role` AS role
            INNER JOIN `sys_user_role` AS ur ON role.id = ur.role_id
            INNER JOIN `sys_user` AS user ON ur.user_id = user.id
            WHERE user.user_name = #{userName}
            """)
    List<SysRole> findUserByRole(@Param("userName") String userName);
}
