package com.example.service;

import com.example.entity.SysMenu;

import java.util.List;

/**
 * @author youshicheng
 * @dateTime 2023-05-13 22:55
 * @apiNote TODO
 */
public interface SysMenuService {

    List<SysMenu> findRoleByMenu(String roleName);
}
