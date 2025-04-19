package com.example.security.authentication;

import com.example.security.bo.LoginUserDetails;
import com.example.security.service.UserDetailsServiceImpl;
import com.example.utils.JwtUtil;
import jakarta.annotation.Resource;
import java.util.Random;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * @author example
 * @dateTime 2023-05-12 19:58
 * @apiNote TODO 自定义认证提供者
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取前端表单中输入后返回的用户名、密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        LoginUserDetails userInfo = (LoginUserDetails) userDetailsService.loadUserByUsername(userName);

        // 将用户传进来的密码进行hash算法加密，过程不可逆，所以需要加密后进行比对
        String encode = BCrypt.hashpw(password, "$2$10$shichengqwertyuioplkjhgfdsa");

        boolean isValid = encode.equalsIgnoreCase(userInfo.getPassword());
        // 验证密码
        if (!isValid) {
            throw new BadCredentialsException("密码错误！");
        }

        // 前后端分离情况下 处理逻辑...
        // 更新登录令牌
        //  String token = PasswordUtils.encodePassword(String.valueOf(System.currentTimeMillis()), userInfo.getCurrentSysUserInfo().getSalt());

        // 生成jwt访问令牌
        String jwt = JwtUtil.createJwt(
                String.valueOf(new Random(5).nextInt())
                , userInfo.getCurrentSysUserInfo().getId().toString()
                , null);

        userInfo.getCurrentSysUserInfo().setToken(jwt);
        // 使用三个参数的构造方法，用以表示为通过认证
        return new UsernamePasswordAuthenticationToken(userInfo, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
