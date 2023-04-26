package com.example.config;

import com.alibaba.fastjson2.JSON;
import com.example.domain.LoginUser;
import com.example.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.PrintWriter;

/**
 * @author 游诗成
 * @date 2022/07/30 20:53
 * @apiNote
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final AuthenticationConfiguration authenticationConfiguration;


    private final UserDetailsServiceImpl userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return authenticationManager;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 关闭csrf
                .csrf().disable()
                // 关闭cors
                .cors().disable()

                // 设置哪些路径可以直接访问不需要认证(permitAll()表示允许所有人访问)
                .authorizeHttpRequests().requestMatchers("/").permitAll()
                .requestMatchers("/user/login").anonymous()
                .anyRequest().authenticated()

                .and()
                // 使用表单登录(不定义登陆页面，就会使用默认界面)
                .formLogin()
                // 定义登陆页面(这里有人喜欢使用index.html的形式，但是需要放在static路径下)
                .loginPage("/toLoginForm")
                // 定义登陆提交表单的action,这里的话只是给一个象征性的接口，并不会去执行这个接口内容，因此也没必要在这个接口里处理业务逻辑
                // 需要注意的是这个表单请求必须是Post请求
                .loginProcessingUrl("/user/login")
                // 对应表单里的提交用户名称
                .usernameParameter("username")
                .passwordParameter("password")
                // 这个设置必须有，否则这个表单登陆设置不起作用
                .permitAll()
                // 成功登录认证处理器
                // .successHandler((req, resp, authentication) -> {
                //     Object principal = authentication.getPrincipal();
                //     resp.setContentType("application/json;charset=utf-8");
                //     PrintWriter out = resp.getWriter();
                //     out.write(JSON.toJSONString(principal));
                //     out.flush();
                //     out.close();
                // })
                // 登录认证失败处理器

                // .failureHandler((req, resp, authentication) -> {
                //     resp.setContentType("application/json;charset=utf-8");
                //     PrintWriter out = resp.getWriter();
                //     out.write("登录认证失败");
                //     out.flush();
                //     out.close();
                // })
                .defaultSuccessUrl("/user/index")
                .and()
                .logout()
                .logoutUrl("/logout")
                // 注销处理器
                // .logoutSuccessHandler((req, resp, authentication) -> {
                //     LoginUser principal = (LoginUser) authentication.getPrincipal();
                //     resp.setContentType("application/json;charset=utf-8");
                //     PrintWriter out = resp.getWriter();
                //     out.write(principal.getUsername() + "退出登录！");
                //     out.flush();
                //     out.close();
                // })
                .permitAll()
        ;

        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
