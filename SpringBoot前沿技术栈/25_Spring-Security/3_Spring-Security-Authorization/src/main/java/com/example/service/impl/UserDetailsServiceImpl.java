package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dto.LoginUser;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author 游诗成
 * @date 2022/07/07
 * @apiNote 用户详情服务实现类(UserDetailsService 这个类是Spring Security提供的, 用于获取用户详情)
 * 实现这个类并重写其方法，是为了可以不使用Spring Security的登录认证机制，而是自己实现登录认证机制
 * 这里我们就 用户详情：
 * 1、如果账号不存在，则抛出异常，提示 用户名或者密码错误
 * 2、如果存在账号，密码错误，则不会抛出异常，但是界面会显示 用户名或者密码错误。
 * 3、如果账号密码都和数据库查询结果一致，则返回用户详情，并且把用户详情放入SecurityContextHolder中
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
     * 5、调用 UserDetailsService方法 loadUserByUsername 方法查询用户
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
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));

        /**
         * 补充：这里的话是没有用到mybatis的一级缓存的，我们可以在控制台打印中看到。按理来说，一级缓存的是默认开启的
         *      我们只需要加上@Transactional注解即可实现一级缓存。原因可在SSM框架里的mybatis详解中了解
         */

        // 如果没有查询到用户就抛出异常
        Optional.ofNullable(user)
                .orElseThrow(() -> new RuntimeException("用户名不存在!!!"));

        List<String> permsssion = user.getPermssion();

        /**
         * 这里需要注意的点就是：spring security把权限和角色放一起了
         * 权限：设置和使用时，名称保持一至即可。
         * 角色：授权代码需要加ROLE_前缀(数据库取出来的数据要有前缀ROLE_)，controller上使用时不要加前缀。
         */

        return new LoginUser(user, permsssion);
    }
}