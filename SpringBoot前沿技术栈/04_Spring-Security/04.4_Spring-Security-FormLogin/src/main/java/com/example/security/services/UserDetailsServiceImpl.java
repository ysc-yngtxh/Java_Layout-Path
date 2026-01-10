package com.example.security.services;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.security.bo.LoginUserDetails;
import com.example.mapper.UserMapper;
import com.example.pojo.po.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 游家纨绔
 * @date 2022-07-07 14:30
 * @apiNote TODO 用户详情服务实现类(UserDetailsService 这个类是Spring Security提供的，用于获取用户详情)
 *               实现这个类并重写其方法，是为了可以不使用Spring Security的登录认证机制，而是自己实现登录认证机制
 *               这里我们就 用户详情：
 *               1、如果账号不存在，则抛出异常，提示 用户名或者密码错误
 *               2、如果存在账号，密码错误，则不会抛出异常，但是界面会显示 用户名或者密码错误。
 *               3、如果账号密码都和数据库查询结果一致，则返回用户详情，并且把用户详情放入SecurityContextHolder中
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	/**
	 * 概要速查：
	 * 1、Authentication接口：它的实现类，表示当前访问系统的用户，封装了用户相关信息
	 * 2、AuthenticationManager接口：定义了认证Authentication的方法，返回一个Authentication对象
	 * 3、UserDetailsService接口：加载用户特定数据的核心接口，里面定义了一个根据用户名查询用户信息的方法。
	 * 4、UserDetails接口：提供核心用户信息。通过UserDetailsService根据用户名或去处理的用户信息要封装UserDetails对象返回，然后将这些信息封装到Authentication对象中。
	 */

	private final UserMapper userMapper;

	/**
	 * 认证流程：
	 * 1、提交用户名密码
	 * 2、封装 Authentication 对象，这时候只有用户名和密码，权限还没有
	 * 3、调用 authenticate 方法进行认证
	 * 4、调用 DaoAuthenticationProvider 的 authenticate 方法进行认证
	 * 5、调用 loadUserByUsername 方法查询用户
	 * 6、返回 UserDetails 对象
	 * 7、通过 PasswordEncode 对比 UserDetails 中的密码和 Authentication 的密码是否正确
	 * 8、如果正确就把 UserDetails 中的权限信息设置到 Authentication 对象中
	 * 9、返回 Authentication 的对象
	 * 10、如果上一步返回了 Authentication 对象就使用SecurityContextHolder.getContext().setAuthentication方法存储该对象
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		System.out.println("执行了loadUserByUsername方法===================");
		// 查询用户信息
		// 创建LambdaQuery构造器的三种方法
		User user1 = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
		User user2 = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName, username));
		User user3 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, username));
		List<User> users = userMapper.selectList(null);
		System.out.println(users);
		/** 补充：
         *  这里的话是没有用到mybatis的一级缓存的，我们可以在控制台打印中看到。按理来说，一级缓存的是默认开启的，
		 *  我们只需要加上@Transactional注解即可实现一级缓存。原因可在SSM框架里的mybatis详解中了解
		 */

		// 如果没有查询到用户就抛出异常
		Optional.ofNullable(user1)
		        .orElseThrow(() -> new RuntimeException("用户名或者密码错误!!!"));

		// TODO 查询对应的权限信息

		// 把数据封装成UserDetails返回
		/** 1、对应的用户信息包括去权限信息封装成 UserDetails 对象。
		 *  2、通过 PasswordEncoder 对比 UserDetails 中的密码和 Authentication 中的密码是否一致，
		 *    如果一致则放入 SecurityContextHolder中(密码是否过期、是否锁定等)
		 *     ①、实际项目中我们不会把密码明文形式存放数据库。默认的 PasswordEncoder 要求数据库密码格式 {id}password，它会根据id去判断加密方式。
		 *         比如账号'Youjiawanku'的密码'{noop}123456'。而Authentication的密码是'123456'，比对后登陆成功!
		 *     ②、但我们一般不会使用这种方式，而是采用 SpringSecurity 为我们提供的 BCryPasswordEncoder 加密方式。
		 *     ③、定义一个 SpringSecurity 配置类，加上@EnableWebSecurity注解,把 BCryPasswordEncoder 对象注入到Spring容器中，
		 *        SpringSecurity就会使用该PasswordEncoder对象来进行密码校验。
		 *  3、如果正确就把UserDetails中的权限信息设置到Authentication中。
		 **/
		return new LoginUserDetails(user1);
	}
}
