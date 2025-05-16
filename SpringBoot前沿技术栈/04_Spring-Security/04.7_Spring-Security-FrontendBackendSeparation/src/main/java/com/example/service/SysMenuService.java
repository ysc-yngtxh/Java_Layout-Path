package com.example.service;

import com.example.pojo.entity.SysMenu;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-13 22:50
 * @apiNote TODO
 */
public interface SysMenuService {

    List<SysMenu> findRoleByMenu(String roleName);
}
