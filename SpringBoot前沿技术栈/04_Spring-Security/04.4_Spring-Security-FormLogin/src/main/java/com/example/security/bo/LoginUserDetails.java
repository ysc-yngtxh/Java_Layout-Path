package com.example.security.bo;

import com.example.pojo.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;

/**
 * @author 游家纨绔
 * @description: 实现UserDetails接口，实现自定义的账号密码登录
 * @date 2022-07-30 20:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDetails implements UserDetails {
	@Serial
	private static final long serialVersionUID = 3691360652528252534L;

	private transient User user;

	/**
	 * 返回用户的权限
	 *
	 * @return Collection<? extends GrantedAuthority>
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	/**
	 * 原返回值是null，这里返回user的密码(需要注意的是：这里获取的密码是要和界面输入的密码进行进行比对的，所以要改成user的密码)
	 *
	 * @return String
	 */
	@Override
	public String getPassword() {
		return user.getPassWord();
	}

	/**
	 * 原返回值是null，这里返回user的账号(这里的账号不需要比对)
	 *
	 * @return String
	 */
	@Override
	public String getUsername() {
		return user.getUserName();
	}

	/**
	 * 账号是否过期,原返回值是false(为false时，表示账号已经过期)
	 *
	 * @return boolean
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 账号是否锁定,原返回值是false(为false时，表示账号锁定)
	 *
	 * @return boolean
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 密码是否过期,原返回值是false(为false的话，会报错：Credentials expired)
	 *
	 * @return boolean
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 账号是否可用,原返回值是false(为false时，会抛出异常，说明账号不可用)
	 *
	 * @return boolean
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
