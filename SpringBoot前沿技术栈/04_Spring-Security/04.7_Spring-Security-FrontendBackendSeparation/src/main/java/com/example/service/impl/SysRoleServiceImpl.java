package com.example.service.impl;

import com.example.pojo.entity.SysRole;
import com.example.mapper.SysRoleMapper;
import com.example.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author 游家纨绔
 * @since 2023-05-13 16:50:00
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	private SysRoleMapper roleMapper;


	/**
	 * @param userName String
	 * @return List<SysRole>
	 */
	public List<SysRole> findUserByRole(String userName) {
		return roleMapper.findUserByRole(userName);
	}
}
