package com.example.config;

import com.example.encoding.PlainTextPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-17 07:54
 * @apiNote TODO 配置类
 */
@Configuration
public class SecurityConfig {

    /**
     * Spring Security 本身并没有默认的密码校验算法。
     * 从SpringSecurity 5.0开始，框架强制要求使用PasswordEncoder接口来处理密码，并且需要明确配置一个实现类来指定具体的密码编码和校验逻辑。
     * 官方推荐使用 DelegatingPasswordEncoder，它支持多种编码格式，并且可以透明地处理不同类型的哈希算法。
     * 这样，即使未来需要更改密码编码策略，也可以平滑过渡而不需要重新编码所有现有密码。
     *
     * NoOpPasswordEncoder 表示不进行任何密码编码的密码编码器，但是明文存储密码是极其不安全，因此官方标记已过时。
     * 但在实际业务中可能还需要不进行编码的密码编码器，后续版本可能会讲过时的类删除。所以可自定义一个明文的密码解码器。
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        // return NoOpPasswordEncoder.getInstance(); 官方标记过时的密码编码器
        return new PlainTextPasswordEncoder(); // 自定义的不进行编码的密码编码器
    }

    /**
     * 通过缓存类过滤器，去实现我们的自定义用户。
     * SpringSecurity要求必须至少有一种权限，这里先各自给上一种
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(){
        // 不论是 'admin' 还是 'manager'，都是拥有对所有接口进行访问的权限。因为我们并没有对接口进行权限限制。
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(
                User.withUsername("admin").password("admin").authorities("admin").build(),
                User.withUsername("manager").password("manager").authorities("manager").build()
        );
        return userDetailsManager;
    }
}
