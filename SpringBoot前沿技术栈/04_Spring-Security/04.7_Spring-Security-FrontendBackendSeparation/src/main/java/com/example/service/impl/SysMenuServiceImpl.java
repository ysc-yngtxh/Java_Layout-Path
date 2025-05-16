package com.example.service.impl;

import com.example.pojo.entity.SysMenu;
import com.example.mapper.SysMenuMapper;
import com.example.service.SysMenuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-13 22:50
 * @apiNote TODO
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Resource
	private SysMenuMapper sysMenuMapper;

	@Override
	public List<SysMenu> findRoleByMenu(String roleName) {
		return sysMenuMapper.findRoleByMenu(roleName);
	}
}
