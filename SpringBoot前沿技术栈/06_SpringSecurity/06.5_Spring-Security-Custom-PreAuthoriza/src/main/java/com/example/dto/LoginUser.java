package com.example.dto;

import com.example.pojo.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author example
 * @description TODO 自定义的实现Details类
 * @create 2023-04-16 上午10:38
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1415757646948554315L;

    private User user;

    private List<String> permission;

    // 存储Security所需要的权限信息集合，这里权限敏感数据，不去做序列化
    // 这里的SimpleGrantedAuthority类是GrantedAuthority类的实现类
    // @JSONField(serialize = false)
    private transient List<SimpleGrantedAuthority> authorityList;

    public LoginUser(User user, List<String> permission) {
        this.user = user;
        this.permission = permission;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (Objects.nonNull(authorityList)) {
            return authorityList;
        }
        authorityList = permission.stream().map(SimpleGrantedAuthority::new).toList();
        return authorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassWord();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
