package com.example.service;

import com.example.pojo.entity.SysRole;

import java.util.List;

/**
 * 角色表(SysRole)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-05-13 16:50:50
 */
public interface SysRoleService {

    // 根据用户名查询该用户所有角色
    List<SysRole> findUserByRole(String userName);
}
