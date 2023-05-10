package com.example.config;

import com.alibaba.fastjson2.JSON;
import com.example.domain.LoginUser;
import com.example.domain.ResponseResult;
import com.example.filter.MyAuthenticationFilter;
import com.example.pojo.User;
import com.example.service.impl.UserDetailsServiceImpl;
import com.example.utils.JwtUtil;
import com.example.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * @author 游诗成
 * @date 2022/07/30 20:53
 * @apiNote
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final MyAuthenticationFilter myAuthenticationFilter;


    private final UserDetailsServiceImpl userDetailsService;


    // 认证权限入口 - 未登录的情况下访问所有接口都会拦截到此(除了配置的不需要认证的路径) - 作为异常情况下的逻辑输出
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                if (Objects.nonNull(authException)) {
                    ResponseResult<Void> result =
                            new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "用户认证失败请查询登录", null);
                    String jsonString = JSON.toJSONString(result);
                    WebUtil.renderText(response, jsonString);
                }
            }
        };
    }

    // 登录认证成功后的业务逻辑，但需要进行配置，这里我没有配置所以不会被执行
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                User user = new User();
                LoginUser loginUser = (LoginUser) authentication.getPrincipal();
                String token = loginUser.getCurrentUserInfo().getToken();
                WebUtil.renderText(response, token);
            }
        };
    }

    // 登录认证失败后的业务逻辑，但需要进行配置，这里我没有配置所以不会被执行
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                System.out.println("登录失败");
                response.sendRedirect("/");
            }
        };
    }

    // 定义一个鉴权失败的异常处理
    // 认证url权限 - 登录后访问接口无权限 - 自定义403无权限响应内容
    // 登录过后的权限处理 【注：要和未登录时的权限处理区分开哦~】
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                ResponseResult<Void> result =
                        new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "您的权限不足！", null);
                String jsonString = JSON.toJSONString(result);
                WebUtil.renderText(response, jsonString);
            }
        };
    }

    /**
     * Spring Security的过滤器链
     * (UsernamePasswordAuthenticationFilter => ExceptionTranslationFilter => FilterSecurityInterceptor)
     *    1、UsernamePasswordAuthenticationFilter 负责处理我们在登录页面填写了用户名密码后的登录请求，入门案例的认证工作主要由他负责
     *    2、ExceptionTranslationFilter 处理过滤器链中抛出的任何AccessDeniedException和AuthenticationException
     *    3、FilterSecurityInterceptor 负责权限校验的过滤器
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 关闭csrf
                .csrf().disable()
                // 关闭cors
                .cors().disable()
                .addFilterBefore(myAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                // 配置异常处理器
                .exceptionHandling()
                // 登录前访问需要认证的路径出现认证异常的，被认证异常返回
                .authenticationEntryPoint(authenticationEntryPoint())
                // 登录后去访问该认证用户没有权限的路径时，被鉴权异常返回
                .accessDeniedHandler(accessDeniedHandler()).and().anonymous()
                .and()

                // 设置哪些路径可以直接访问不需要认证(permitAll()表示允许所有人访问)
                .authorizeHttpRequests().requestMatchers("/").permitAll()
                .requestMatchers("/user/login").anonymous()  // anonymous()表示匿名访问（登陆提交接口通常都是匿名访问）
                .requestMatchers("/code").hasAuthority("admin")
                // .requestMatchers("/static/**").permitAll()
                // 其余的都需要认证校验(拦截)
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
                // .successForwardUrl("/toMain")
                // 重载方法一：当只有一个参数时，表示一开始用户访问路径不是登录路径时，在登陆后跳转到访问路径。
                //           但是一开始用户访问登陆路径时，那么登陆跳转的就是该方法的参数路径/toMain。
                // 重载方法二：当存在两个参数，且第二个参数值为true时，表示无论用户在登陆前方法的是哪个路径，在登陆后跳转的路径始终都是/toMain
                //           效果同successForwardUrl("/toMain")
               .defaultSuccessUrl("/user/index")
                // 认证成功被调用：区别于defaultSuccessUrl("/user/index")，这里可以进行逻辑书写，也可以重定向和请求转发，功能更强大
                // .successHandler(authenticationSuccessHandler())
                // 认证失败被调用
               .failureHandler(authenticationFailureHandler())
               ;
       /**
         * 这里有一个问题：
         *       就是defaultSuccessUrl()我们只给一个参数，那么当我们一开始访问http://localhost:8080,
         *   因为没有认证就会被拦截重定向到登陆路径loginPage("/user/index") ------ http://localhost:8080/user/index
         *   这个时候我们登录跳转，但由于我们并没有设置第二个参数，就会导致我们跳转的是我们一开始访问的路径http://localhost:8080
         *   然而这个路径"/"我们并没有在Controller中定义,所以会被Spring Security拦截认为是恶意访问，需要再一次进行认证
         *   然后又被重定向 http://localhost:8080/user/index,所以又会出现登陆后跳转到登陆前的页面。
         *
         *   解决这种问题方案：一、在requestMatchers()中加上"/"路径，用来排除需要认证的拦截，那么在我们跳转"/"路径时就不需要再被拦截，
         *                     然后会发现在Controller里并没有"/"路径，但是我们排除了认证，那么就会跳转到在defaultSuccessUrl()方法的/toMain
         *                  二、在defaultSuccessUrl()中加上第二个参数true，那么登陆成功后就不会跳转到一开始访问的路径
         */

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                Authentication result = authenticationProvider().authenticate(authentication);
                if (Objects.nonNull(result)) {
                    return result;
                }
                throw new ProviderNotFoundException("Authentication failed!");
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                // 获取前端表单中输入后返回的用户名、密码
                String userName = (String) authentication.getPrincipal();
                String password = (String) authentication.getCredentials();

                LoginUser userInfo = (LoginUser) userDetailsService.loadUserByUsername(userName);

                // 将用户传进来的密码进行hash算法加密，过程不可逆，所以需要加密后进行比对
                String encode = BCrypt.hashpw(password, "$2$10$shichengqwertyuioplkjhgfdsa");

                boolean isValid = encode.equalsIgnoreCase(userInfo.getPassword());
                // 验证密码
                if (!isValid) {
                    throw new BadCredentialsException("密码错误！");
                }

                // 前后端分离情况下 处理逻辑...
                // 更新登录令牌
                //  String token = PasswordUtils.encodePassword(String.valueOf(System.currentTimeMillis()), userInfo.getCurrentUserInfo().getSalt());

                // 生成jwt访问令牌
                String jwt = JwtUtil.createJwt(userInfo.getCurrentUserInfo().getId().toString(), "Subject", null);

                userInfo.getCurrentUserInfo().setToken(jwt);
                return new UsernamePasswordAuthenticationToken(userInfo, password, userInfo.getAuthorities());

            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        };
    }


    // 密码加密编码方式不使用默认的加密方式，使用BCryptPasswordEncoder
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public AuthenticationManager authenticationManagerBean() throws Exception {
    //     return authenticationConfiguration.getAuthenticationManager();
    // }


    // @Bean
    // public AuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //     provider.setUserDetailsService(userDetailsService);
    //     provider.setPasswordEncoder(passwordEncoder());
    //     return provider;
    // }

}
