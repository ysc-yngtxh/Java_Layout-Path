package com.example.config;

import com.example.encoding.PlainTextPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // TODO CSRF（跨站请求防伪造）写法一：
        // 自 SpringSecurity4.0 起，CSRF 跨站请求防伪造保护是默认开启的，因此有开启需要的话不用特意书写这行代码。
        // 当使用这种写法时，Cookie 中不包含CSRF令牌信息，前端可以通过MVC返回视图中获取 ${_csrf.token} 来获取 CSRF令牌。
        http.csrf(Customizer.withDefaults());

        // TODO CSRF（跨站请求防伪造）写法二：
        // 这里的 CSRF 配置比较适用于 前后端分离的项目。服务器会在请求的 Cookie 中生成一个 XSRF-TOKEN 值。
        // 前端在每次请求时，需要携带一个名称为 X-XSRF-TOKEN 的值，服务器会验证这个值是否正确。
        // 如果验证失败，服务器会返回一个 403 错误，表示请求被拒绝。
        http.csrf(csrf ->
                // 该配置为前端设置一个名为 XSRF-TOKEN 的 Cookie。
                // 并且将 HTTP-only 标志设置为 false，因此前端还可以使用 JavaScript 获取此 Cookie。
                csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    // TODO 该配置可以保持Cookie 中的 XSRF-TOKEN令牌值 与请求中的 CSRF令牌值 一致，但是会有 BREACH 攻击风险。
                    //  CSRF漏洞：
                    //    Spring 总是向浏览器返回相同的 CSRF 令牌。CSRF 令牌容易受到 BREACH 攻击.
                    //  SpringSecurity解决策略：
                    //    1、由一个随机的每个密钥请求与内部 CSRF 令牌进行 XOR 运算返回一个新的令牌。这实际上意味着浏览器在每个请求中都会收到一个新的 CSRF 令牌。
                    //    2、SpringSecurity默认的 csrf 请求处理器为 XorCsrfTokenRequestAttributeHandler，防止CSRF令牌和其他敏感信息遭受BREACH攻击，
                    //       而 XorCsrfTokenRequestAttributeHandler 处理器会将原生 CSRF令牌 进行加密生成新的 CSRF令牌.
                    //    3、这时候 Cookie 中的 XSRF-TOKEN令牌值，与MVC返回视图中的 CSRF令牌值 是不一致的。
                    //       要想请求正常访问，就只能在 请求参数 或者 请求头 中携带使用新的令牌值（即MVC返回视图中的 CSRF 令牌值）
                    //    4、当然，我们也可以选择不使用默认的 csrf 请求处理器，
                    //       这里定义其他的处理器，那么Cookie 中的 XSRF-TOKEN令牌值 与MVC返回视图中的 CSRF令牌值 就是一致的。
                    //       就可以在 请求参数 或者 请求头 中携带使用Cookie 中解析的 XSRF-TOKEN令牌值.
                    .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                    // .ignoringRequestMatchers("/login")  该配置能忽略 CSRF 的请求路径
        );

        http
                // 配置 HTTP Basic 身份验证。
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/static/**").permitAll()
                                .anyRequest().authenticated()
                );

        return http.build();
    }
}
