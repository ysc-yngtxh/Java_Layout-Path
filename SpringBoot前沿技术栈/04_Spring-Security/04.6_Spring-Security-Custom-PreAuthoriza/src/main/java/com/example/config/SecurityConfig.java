package com.example.config;

import com.alibaba.fastjson2.JSON;
import com.example.pojo.vo.ResponseResult;
import com.example.security.bo.LoginUserDetails;
import com.example.security.service.UserDetailsServiceImpl;
import com.example.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author 游家纨绔
 * @date 2022-07-30 20:00
 * @apiNote
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationConfiguration authenticationConfiguration;

	private final UserDetailsServiceImpl userDetailsService;

	/**
	 * Spring Security 本身并没有默认的密码校验算法。
	 * 从SpringSecurity 5.0开始，框架强制要求使用PasswordEncoder接口来处理密码，并且需要明确配置一个实现类来指定具体的密码编码和校验逻辑。
	 * 官方推荐使用 DelegatingPasswordEncoder，它支持多种编码格式，并且可以透明地处理不同类型的哈希算法。
	 * 这样，即使未来需要更改密码编码策略，也可以平滑过渡而不需要重新编码所有现有密码。
	 * <p>
	 * 这里密码加密编码方式使用的是 BCryptPasswordEncoder.
	 * <p>
	 * 在Spring Security中，如果你选择使用明文存储密码（生产环境中是非常不推荐），你必须在密码前加上 {noop} 前缀。
	 * {noop} 前缀表明这是一个故意以明文形式存储的密码。
	 * 这种情况下，Spring Security会跳过所有密码编码器，直接将用户输入的密码与数据库中存储的明文密码进行比较。
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	// 认证权限入口 - 未登录的情况下访问所有接口都会拦截到此(除了配置的不需要认证的路径) - 作为异常情况下的逻辑输出
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
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
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
				LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
				String token = loginUserDetails.getUser().getToken();
				WebUtil.renderText(response, token);
			}
		};
	}

	// 登录认证失败后的业务逻辑，但需要进行配置，这里我没有配置所以不会被执行
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
				ResponseResult<Void> result =
						new ResponseResult<>(HttpStatus.FORBIDDEN.value(), exception.getMessage(), null);
				String jsonString = JSON.toJSONString(result);
				WebUtil.renderText(response, jsonString);
			}
		};
	}

	// 定义一个鉴权失败的异常处理
	// 认证url权限 - 登录后访问接口无权限 - 自定义403无权限响应内容
	// 登录过后的权限处理 【注：要和未登录时的权限处理区分开哦~】
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
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

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// 关闭csrf
				.csrf().disable()
				// 关闭cors
				.cors().disable()

				// 设置哪些路径可以直接访问不需要认证(permitAll()表示允许所有人访问)
				.authorizeHttpRequests().requestMatchers("/").permitAll()
				.requestMatchers("/user/login").anonymous()  // 表示该路径可以被匿名访问
				.anyRequest().authenticated()
				.and()


				// 配置异常处理器
				.exceptionHandling()
				// 登录前访问需要认证的路径出现认证异常的，被认证异常返回
				.authenticationEntryPoint(authenticationEntryPoint())
				// 登录后去访问该认证用户没有权限的路径时，被鉴权异常返回
				.accessDeniedHandler(accessDeniedHandler()).and().anonymous()
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

				// 设置认证失败后的逻辑处理
				.failureHandler(authenticationFailureHandler())
				// 登录认证失败处理器
				// .failureHandler((req, resp, authentication) -> {
				//     resp.setContentType("application/json;charset=utf-8");
				//     PrintWriter out = resp.getWriter();
				//     out.write("登录认证失败");
				//     out.flush();
				//     out.close();
				// })
				.defaultSuccessUrl("/user/index")


				/** 可依照默认SpringSecurity的 LogoutConfigurer 类，不进行配置。如果自定义的前端登出接口是/logout，那么就会使用SpringSecurity的内置接口/logout
				 *  内置接口则会执行 logoutUrl("/logout"); 清除HttpSession，Cookie，用户信息SecurityContextHolder 等。
				 *  还会执行 logoutSuccessUrl("/toLoginForm?logout"); 这里的路径会根据自己的设置的登录路径替换。*/
				// TODO 需要注意的是：我们没有进行登出配置，因此默认登出的时候重定向请求到/toLoginForm,
				//       这里配置了认证权限入口 AuthenticationEntryPoint ，登录认证成功前，除排除路径外，会拦截所有的接口,
				//       因此有两种方法：一、在排除认证的路径加上requestMatchers("/","toLoginForm").permitAll()
				//                    二、配置自己的logout，并允许所有人访问.permitAll()
				.and()
				.logout()
				// 表单退出登录的执行路径，类似于登录一样，存在一个提交路径，实际不会执行路径逻辑。
				.logoutUrl("/cancellation")
				// 表单退出登录，成功后执行的操作路径。这里不进行配置，就会默认的重定向到登陆路径/toLoginForm
				.logoutSuccessUrl("/logout")
				// 注销处理器
				.logoutSuccessHandler((req, resp, authentication) -> {
					LoginUserDetails principal = (LoginUserDetails) authentication.getPrincipal();
					resp.setContentType("application/json;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.write(principal.getUsername() + "退出登录！");
					out.flush();
					out.close();
				})
				.permitAll()
		;

		return http.build();
	}
}
