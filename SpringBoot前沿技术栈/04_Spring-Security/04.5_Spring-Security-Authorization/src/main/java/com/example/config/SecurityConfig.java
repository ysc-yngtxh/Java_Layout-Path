package com.example.config;

import com.example.security.service.UserDetailsServiceImpl;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
	 * 这里密码加密编码方式使用的是 DelegatingPasswordEncoder.
	 * <p>
	 * 在Spring Security中，如果你选择使用明文存储密码（生产环境中是非常不推荐），你必须在密码前加上 {noop} 前缀。
	 * {noop} 前缀表明这是一个故意以明文形式存储的密码。
	 * 这种情况下，Spring Security会跳过所有密码编码器，直接将用户输入的密码与数据库中存储的明文密码进行比较。
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder();
		encoders.put("bcrypt", cryptPasswordEncoder);
		// 添加明文编码器（不推荐用于生产环境）
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		encoders.put("null", cryptPasswordEncoder);

		// DelegatingPasswordEncoder 用于创建委托模式的密码编码器。
		// ①、加密密码时：会使用指定的编码器（构造方法的参数一）进行加密，且加密后得到的密码会有前缀（如 {bcrypt}、{noop}）。
		//              需要注意的是：指定的编码器必须在 Map（构造方法的参数二） 中存在。
		// ②、解析密码时：会先根据密码的前缀（如 {bcrypt}、{noop}）来选择对应的编码器（构造方法的参数二）进行解析。
		//     问题：如果解析密码没有前缀，那么会默认尝试用 null 作为编码器Id，但 Map 中没有 null 这个 key，因此报错。
		//     方案：
		//       Ⅰ、提供一个 null 编码器：encoders.put("null", new BCryptPasswordEncoder());
		//       Ⅱ、设置默认编码器：delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());
		DelegatingPasswordEncoder delegatingPasswordEncoder =
				new DelegatingPasswordEncoder("bcrypt", encoders);
		// 如果解析的密码没有前缀，配置使用 bcrypt 编码器方式进行解析
		delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(cryptPasswordEncoder);
		return delegatingPasswordEncoder;
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

	/**
	 * Spring Security的过滤器链
	 * (UsernamePasswordAuthenticationFilter => ExceptionTranslationFilter => AuthorizationFilter )
	 * 1、UsernamePasswordAuthenticationFilter 负责处理我们在登录页面填写了用户名密码后的登录请求，入门案例的认证工作主要由他负责
	 * 2、ExceptionTranslationFilter 处理过滤器链中抛出的任何AccessDeniedException和AuthenticationException
	 * 3、AuthorizationFilter  负责权限校验的过滤器
	 * <p>
	 * 注意：在配置类中引入了 SecurityFilterChain 类型的Bean，就不会显示Spring Security的默认登陆界面了。
	 * 如果有使用默认的登陆界面的必要，可以在 securityFilterChain 方法中加上 .and().formLogin() 就可以显示默认。
	 * 另外，只使用 formLogin()，不进行表单的其他配置，控制台还是可以打印出登陆的默认密码；但是如果有配置其他的表单内容，控制台将不打印默认的登陆密码。
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// 关闭csrf
				.csrf().disable()
				// 关闭cors
				.cors().disable()

				// 这里我们先注释掉，在访问页面的时候我们的每一个请求，都会从携带的Cookie中找到key为JSESSIONID的value值去和服务器的任何一个session的id进行比对，
				// 如果不存在，则服务器就创建一个新的session，并同时创建一个key为JSESSIONID，value为该session的id的cookie并响应给浏览器写入
				// 如果存在，那么这个key为JSESSIONID的Cookie将一直存在于此次的会话里，用于辨别该用户的操作
				// 需要注意的是，我们在登录后(登陆前可被视为游客)，这个key为JSESSIONID,value为该session的id将会被重新创建，而这个创建则是被指定为Spring Security来创建这个session
				// 原因就在于，我们登陆后所需要的是否认证都是通过这个由Spring Security来创建的key为JSESSIONID的session来判断
				// 如果存在表明该用户已经进行过认证，可以放行访问; 如果不存在就会跳转到登陆页面，需要用户进行登录认证
				// 所以这里不通过Session获取SecurityContext的设置我们暂时不需要，毕竟我们这个需要通过session来获取
				// 这里我们前后端项目没有分离，所以需要通过JSESSIONID来建立一个会话连接;当我们做前后端分离的项目就可以设置 不通过Session获取SecurityContext
				// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				// .and()

				// 设置哪些路径可以直接访问不需要认证(permitAll()表示允许所有人访问)
				.authorizeHttpRequests().requestMatchers("/").permitAll()
				.requestMatchers("/user/login").anonymous()  // anonymous()表示匿名访问（登陆提交接口通常都是匿名访问）
				.requestMatchers("/code").hasAuthority("admin") // 需要 admin 的权限才能访问
				.requestMatchers("/menu").hasRole("ADMIN") // 需要 admin 的角色才能访问（对应的数据库中的 ROLE_ADMIN）
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
				// 成功登录认证处理器
				// .successHandler((req,resp,authentication)->{
				//             Object principal = authentication.getPrincipal();
				//             resp.setContentType("application/json;charset=utf-8");
				//             PrintWriter out = resp.getWriter();
				//             out.write(JSON.toJSONString(principal));
				//             out.flush();
				//             out.close();
				//         });
				// 登录成功后跳转的路径
				// .successForwardUrl("/toMain")
				// 重载方法一：当只有一个参数时，表示一开始用户访问路径不是登录路径时，在登陆后跳转到访问路径。
				//           但是一开始用户访问登陆路径时，那么登陆跳转的就是该方法的参数路径/toMain。
				// 重载方法二：当存在两个参数，且第二个参数值为true时，表示无论用户在登陆前方法的是哪个路径，在登陆后跳转的路径始终都是/toMain
				//           效果同successForwardUrl("/toMain")
				.defaultSuccessUrl("/user/index")


				/** 可依照默认SpringSecurity的 LogoutConfigurer 类，不进行配置。如果自定义的前端登出接口是/logout，那么就会使用SpringSecurity的内置接口/logout
				 *  内置接口则会执行 logoutUrl("/logout"); 清除HttpSession，Cookie，用户信息SecurityContextHolder 等。
				 *  还会执行 logoutSuccessUrl("/toLoginForm?logout"); 这里的路径会根据自己的设置的登录路径替换。*/
				.and()
				.logout()
				// 表单退出登录的执行路径，类似于登录一样，存在一个提交路径，实际不会执行路径逻辑。
				.logoutUrl("/cancellation")
				// 表单退出登录，成功后执行的操作路径。这里不进行配置，就会默认的重定向到登陆路径/toLoginForm
				.logoutSuccessUrl("/logout")
				// 注销处理器
				// .logoutSuccessHandler((req, resp, authentication) -> {
				//     LoginUserDetails principal = (LoginUserDetails) authentication.getPrincipal();
				//     resp.setContentType("application/json;charset=utf-8");
				//     PrintWriter out = resp.getWriter();
				//     out.write(principal.getUsername() + "退出登录！");
				//     out.flush();
				//     out.close();
				// })
				.permitAll()
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
		 *                     然后会发现在Controller里并没有"/"路径，直接被重定向到web的默认欢迎资源文件index.html，
		 *                     但是默认欢迎文件又正好是我们的登陆路径/toLoginForm,那么就会跳转到在defaultSuccessUrl()方法的/toMain
		 *                  二、在defaultSuccessUrl()中加上第二个参数true，那么登陆成功后就不会跳转到一开始访问的路径
		 */

		return http.build();
	}


	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return new WebSecurityCustomizer() {
			@Override
			public void customize(WebSecurity web) {
				// 这里忽略的一般都是一些静态文件，例如js,css等
				web.ignoring().requestMatchers("/static/**");
			}
		};
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("123456"));

		PasswordEncoder passwordEncoder =
				new SecurityConfig(null, null).passwordEncoder();
		System.out.println(passwordEncoder.encode("123456"));
	}
}
