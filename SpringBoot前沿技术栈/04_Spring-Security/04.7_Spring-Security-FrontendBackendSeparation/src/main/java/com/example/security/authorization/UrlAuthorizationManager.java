package com.example.security.authorization;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.pojo.entity.SysMenu;
import com.example.pojo.entity.SysRole;
import com.example.pojo.entity.SysRoleMenu;
import com.example.mapper.SysMenuMapper;
import com.example.mapper.SysRoleMapper;
import com.example.mapper.SysRoleMenuMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * <p> 对访问url进行权限认证处理 </p>
 *
 * @author: 游家纨绔
 * @description: 鉴权管理器
 * @date: 2023-05-15 14:20
 */
@Component
public class UrlAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

	@Resource
	private SysMenuMapper sysMenuMapper;

	@Resource
	private SysRoleMapper sysRoleMapper;

	@Resource
	private SysRoleMenuMapper sysRoleMenuMapper;

	// 有权限：new AuthorizationDecision(true)
	// 无权限：new AuthorizationDecision(false)
	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication,
	                                   RequestAuthorizationContext requestAuthorizationContext) {
		// 获取当前用户访问路径
		HttpServletRequest request = requestAuthorizationContext.getRequest();
		String requestURI = request.getRequestURI();
		// 获取当前用户拥有权限（即该用户的角色 【在Spring Security中，权限跟角色放在一起】）
		Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
		List<String> list = authorities.stream().map(GrantedAuthority::getAuthority).toList();

		// 获取角色Id
		List<SysRole> sysRoles = sysRoleMapper.selectList(Wrappers.<SysRole>lambdaQuery().in(SysRole::getName, list));
		List<Long> roleIds = sysRoles.stream().map(SysRole::getId).toList();
		boolean isAuthorizationUrl = false;
		if (!roleIds.isEmpty()) {
			// 根据角色Id进而获取菜单Id
			List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(Wrappers.<SysRoleMenu>lambdaQuery().in(SysRoleMenu::getRoleId, roleIds));
			List<Long> menuIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).distinct().toList();
			// 获取该用户能访问的所有路径
			List<SysMenu> sysMenus = sysMenuMapper.selectList(new LambdaQueryWrapper<SysMenu>().in(SysMenu::getId, menuIds));

			isAuthorizationUrl = sysMenus.stream().anyMatch(item -> {
				AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(item.getPath(), HttpMethod.POST.toString());
				return antPathRequestMatcher.matches(request);
			});
		}
		if (isAuthorizationUrl) {
			return new AuthorizationDecision(true);
		}
		return new AuthorizationDecision(false);
	}
}
