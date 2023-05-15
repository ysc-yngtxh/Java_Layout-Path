package com.example.authorization;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.example.domain.LoginUser;
import com.example.entity.SysMenu;
import com.example.mapper.SysMenuMapper;
import com.example.mapper.SysRoleMapper;
import jakarta.annotation.Resource;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <p> 对访问url进行权限认证处理 </p>
 *
 * @author : 游家纨绔
 * @description : 鉴权管理器
 * @date : 2023/05/15 14:21
 */
@Component
public class UrlAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Resource
    private SysMenuMapper sysMenuMapper;
    /**
     * 这一个方法不需要重写,原生 verify() 就是调用的下面的 check() 方法
     */
    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    // 有权限：new AuthorizationDecision(true)
    // 无权限：new AuthorizationDecision(false)
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication
            , RequestAuthorizationContext requestAuthorizationContext) {
        // 获取当前用户访问路径
        String requestURI = requestAuthorizationContext.getRequest().getRequestURI();
        // 获取当前用户拥有权限
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        List<String> list = authorities.stream().map(GrantedAuthority::getAuthority).toList();
        Optional.of(list)
                .orElseThrow(() -> new RuntimeException("该用户么有权限！"));

        // 获取该用户能访问的所有路径
        List<SysMenu> sysMenus = sysMenuMapper.selectList(new LambdaQueryWrapper<SysMenu>().in(SysMenu::getPath, list));
        // 该用户所有路径中是否包含当前访问路径
        boolean isAuthorizationUrl = sysMenus.stream().anyMatch(item -> item.getPath().equals(requestURI));
        return isAuthorizationUrl ? new AuthorizationDecision(true) : new AuthorizationDecision(false);
    }
}
