package com.example.config;

import com.alibaba.fastjson2.JSON;
import com.example.filter.AdminAuthenticationProcessingFilter;
import com.example.filter.MyAuthenticationFilter;
import com.example.pojo.vo.ResponseResult;
import com.example.security.authorization.UrlAuthorizationManager;
import com.example.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author 游家纨绔
 * @date 2022-07-30 20:53
 * @apiNote
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter;

	private final MyAuthenticationFilter myAuthenticationFilter;

	private final UrlAuthorizationManager urlAuthorizationManager;

	private final MyAuthorizationProperties myAuthorizationProperties;


	@Bean
	public PasswordEncoder passwordEncoder() {
		HashMap<String, PasswordEncoder> map = new HashMap<>();
		map.put("bcrypt", new BCryptPasswordEncoder());
		map.put("noop", NoOpPasswordEncoder.getInstance());
		DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("bcrypt", map);
		delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
		return delegatingPasswordEncoder;
	}


	// 认证权限入口 - 未登录的情况下访问所有接口都会拦截到此(除了配置的不需要认证的路径) - 作为异常情况下的逻辑输出
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
				if (Objects.nonNull(authException)) {
					ResponseResult<Void> result =
							new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), authException.getMessage(), null);
					String jsonString = JSON.toJSONString(result);
					WebUtil.renderText(response, jsonString);
				}
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
			public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
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
				// 关闭csrf，前后端分离不需要. Spring Security6.2新写法
				.csrf(AbstractHttpConfigurer::disable)
				// 关闭cors跨域. Spring Security6.2新写法
				.cors(AbstractHttpConfigurer::disable)
				// 禁用httpBasic，因为我们传输数据用的是post，而且请求体是JSON
				.httpBasic(AbstractHttpConfigurer::disable)
				// 不创建会话(无状态) - 即通过前端传token到后台过滤器中验证是否存在访问权限。 Spring Security6.2新写法
				.sessionManagement(sessions ->
						                   sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)

				// 见名知意。添加过滤器在某某过滤器之前。这里我们添加在BasicAuthenticationFilter之前的过滤器myAuthenticationFilter
				.addFilterBefore(myAuthenticationFilter, BasicAuthenticationFilter.class)
				// 也是一样的添加过滤器adminAuthenticationProcessingFilter在UsernamePasswordAuthenticationFilter附近
				.addFilterAt(adminAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)


				// 配置异常处理器
				.exceptionHandling(exception ->
						                   exception
								                   // 登录前访问需要认证的路径出现认证异常的，被认证异常返回
								                   .authenticationEntryPoint(authenticationEntryPoint())
								                   // 登录后去访问该认证用户没有权限的路径时，被鉴权异常返回
								                   .accessDeniedHandler(accessDeniedHandler())
				)


				// 设置哪些路径可以直接访问不需要认证（permitAll()表示允许所有人访问）
				.authorizeHttpRequests(authorized ->
						                       authorized
								                       // 通过配置文件进行配置Spring Security的放行路径
								                       // 这里需要注意的是：转成String类型的数组，需要指定转成数组的容量，不能超出也不能少于实际元素个数，
								                       // 否则数组空余的容量部分会替换为null，Spring Security在匹配放行路径时会出现空指针异常。
								                       // 可以写成 toArray(new String[0]) 的方式，这样创建数组，可以确保返回的新数组会进行自动扩容，其大小与集合的大小相同
								                       .requestMatchers(myAuthorizationProperties.getIgnoreUrls().toArray(new String[8])).permitAll()
								                       // 同上述放行路径写法效果一样，但保留以上写法是为了谨记在设置数组容量过大时出现的空指针异常，为此花了两天才定位到BUG。难顶！！！
								                       //.requestMatchers(Arrays.toString(myAuthorizationProperties.getIgnoreUrls().toArray())).permitAll()
								                       // 定义一个需要admin权限的路径/**
								                       // .requestMatchers("/**").hasAuthority("admin")
								                       // OPTIONS(选项)：查找适用于一个特定网址资源的通讯选择。 在不需执行具体的涉及数据传输的动作情况下，
								                       // 允许客户端来确定与资源相关的选项以及 / 或者要求， 或是一个服务器的性能
								                       // denyAll()表示全部拒绝访问
								                       .requestMatchers(HttpMethod.OPTIONS, "/**").denyAll()
								                       // 其余所有请求都需要认证
								                       // .anyRequest().authenticated()
								                       // TODO 注意：这里设置成url权限认证处理
								                       .anyRequest().access(urlAuthorizationManager)
				)

				// 自动登录 - cookie储存方式
				.rememberMe(Customizer.withDefaults())
				// 防止iframe 造成跨域
				.headers(header ->
						         header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
				)
				// 禁用form表单提交的方式
				.formLogin(AbstractHttpConfigurer::disable)
		;
		return http.build();
	}
}
