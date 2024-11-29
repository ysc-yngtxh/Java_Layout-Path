package com.example.oauth2.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-29 15:55
 * @apiNote TODO 自定义OAuth2用户授权
 */
@Service
public class GrantedAuthoritiesMapperImpl implements GrantedAuthoritiesMapper {

    // 可参考 SpringSecurity 中的 UserDetails 类中的 getAuthorities() 方法。
    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {

        // TODO 这里定义的 '角色' 可以通过数据库根据 OAuth2 的账号唯一Id(uniqueId)查询获取，此处为了简便示例使用集合。
        List<String> arrayList = new ArrayList<>();
        arrayList.add("ROLE_USER");
        arrayList.add("ROLE_ADMIN");

        // SpringSecurity 授权中 "ROLE_" 为前缀的表示 '角色'；而非 "ROLE_" 为前缀的表示 '权限'。
        // 而这里自定义的OAuth2用户授权是不建议使用 '权限'。原因在于应用 RBAC 模型，通过角色获取权限，以便更好的控制对系统和资源的访问
        // arrayList.add("vip");

        // GrantedAuthority 的实现类 SimpleGrantedAuthority。将集合中的元素转换为SimpleGrantedAuthority对象
        List<SimpleGrantedAuthority> list = arrayList.stream().map(SimpleGrantedAuthority::new).toList();
        return list;
    }
}
