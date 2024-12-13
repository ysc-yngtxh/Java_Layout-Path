package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
        return NoOpPasswordEncoder.getInstance(); // 官方标记过时的密码编码器
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(Customizer.withDefaults())
            // Remember Me 即记住我，常用于 Web 应用的登录页目的是让用户选择是否记住用户的登录状态。
            // 当用户选择了 Remember Me 选项，则在有效期内若用户重新访问同一个 Web 应用，那么用户可以直接登录到系统中，而无需重新执行登录操作。
            // 实现原理：用户登录成功后，Security会往用户浏览器中写入一个可以为 remember-me 的 Cookie，其值经过了Base64的编码。经过解码后得到的原始值分为四段，使用":"分割。
            //         base64(username + ":" + expirationTime + ":" + algorithmName + ":" + algorithmHex(username+":"+expirationTime+":"password+":"+key))
            //         四段分别表示的含义：用户名：过期时间戳：加密算法名：签名字符串(该字符串由用户名、过期时间戳、密码、开发指定的key值四者结合起来按照算法生成十六进制的加密字符串)
            // RememberMe的Cookie中保存了用户名和密码等敏感信息，虽然加密处理，仍然有被破解的可能。在使用JWT实现登录认证之后，就不必使用RememberMe的功能了。
            .rememberMe(remember ->
                    // 指定Cookie中生成的 remember-me 加密需要的key值，使其编码更加难以被破解
                    remember.key("yjwk")
                            // 修改登陆表单中remember复选框的name值，其默认参数为remember-me
                            .rememberMeParameter("rememberMe")
                            // 修改Cookie中的"记住我"值，其默认参数为remember-me
                            .rememberMeCookieName("rememberMe")
                            // 设置 token 的有效时间
                            .tokenValiditySeconds(30)
            )
            .authorizeHttpRequests(auth -> {
                auth
                        // .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                        .anyRequest().authenticated();
            })
            .logout(Customizer.withDefaults());

        // TODO 非持久化RememberMe功能弊端：重启服务器会导致浏览器Cookie中的remember-me自动删除，从而导致数据丢失无法实现RememberMe功能。因此需要持久化RememberMe功能

        return http.build();
    }
}
