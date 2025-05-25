package com.example.security.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mapper.UserMapper;
import com.example.pojo.po.User;
import com.example.security.bo.LoginUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 游家纨绔
 * @date 2022-07-07 14:30
 * @apiNote 用户详情服务实现类(UserDetailsService 这个类是Spring Security提供的, 用于获取用户详情)
 *          实现这个类并重写其方法，是为了可以不使用Spring Security的登录认证机制，而是自己实现登录认证机制
 *          这里我们就 用户详情：
 *          1、如果账号不存在，则抛出异常，提示 用户名或者密码错误
 *          2、如果存在账号，密码错误，则不会抛出异常，但是界面会显示 用户名或者密码错误。
 *          3、如果账号密码都和数据库查询结果一致，则返回用户详情，并且把用户详情放入SecurityContextHolder中
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) {

		System.out.println("执行了loadUserByUsername方法===================");

		// 查询用户信息
		User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));

		Optional.ofNullable(user)
		        .orElseThrow(() -> new RuntimeException("用户名不存在!!!"));

		List<String> permission = user.getPermission();

		/**
		 * 这里需要注意的点就是：spring security把权限和角色放一起了
		 * 权限：设置和使用时，名称保持一至即可。
		 * 角色：授权代码需要加ROLE_前缀(数据库取出来的数据要有前缀ROLE_)，controller上使用时不要加前缀。
		 */

		return new LoginUserDetails(user, permission);
	}

}
