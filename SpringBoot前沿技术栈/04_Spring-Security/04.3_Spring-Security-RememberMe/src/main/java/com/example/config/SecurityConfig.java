package com.example.config;

import jakarta.annotation.Resource;
import javax.sql.DataSource;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-17 08:00:00
 * @apiNote TODO 配置类
 */
@Configuration
public class SecurityConfig {

	/**
	 * Spring Security 本身并没有默认的密码校验算法。
	 * 从SpringSecurity 5.0开始，框架强制要求使用PasswordEncoder接口来处理密码，并且需要明确配置一个实现类来指定具体的密码编码和校验逻辑。
	 * 官方推荐使用 DelegatingPasswordEncoder，它支持多种编码格式，并且可以透明地处理不同类型的哈希算法。
	 * 这样，即使未来需要更改密码编码策略，也可以平滑过渡而不需要重新编码所有现有密码。
	 * <p>
	 * NoOpPasswordEncoder 表示不进行任何密码编码的密码编码器，但是明文存储密码是极其不安全，因此官方标记已过时。
	 * 但在实际业务中可能还需要不进行编码的密码编码器，后续版本可能会讲过时的类删除。所以可自定义一个明文的密码解码器。
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // 官方标记过时的密码编码器
	}

	/**
	 * 通过缓存类过滤器，去实现我们的自定义用户。
	 * SpringSecurity要求必须至少有一种权限，这里先各自给上一种
	 *
	 * @return UserDetailsService
	 */
	@Bean
	public UserDetailsService userDetailsService() {
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
				// 配置"记住我"功能
				.rememberMe(remember ->
						            // 指定Cookie中生成的 remember-me 加密需要的key值，使其编码更加难以被破解
						            remember.key("yjwk")
						                    // 修改登陆表单中remember复选框的name值，其默认参数为remember-me
						                    .rememberMeParameter("rememberMe")
						                    // 设置浏览器中存储的Cookie名称，其默认参数为remember-me
						                    .rememberMeCookieName("rememberMe")
						                    // 设置 token 的有效时间(单位：秒)。Security默认时长为14天(两周)
						                    .tokenValiditySeconds(30)
						                    // 设置操作数据表的 Repository
						                    .tokenRepository(persistentTokenRepository())
				            // .userDetailsService(userDetailsService())
				           )
				.authorizeHttpRequests(auth -> {
					auth.anyRequest().authenticated();
				})
				.logout(Customizer.withDefaults());

		return http.build();
	}

	@Resource
	private DataSource dataSource;

	/**
	 * 配置 JdbcTokenRepositoryImpl，用于 Remember-Me 的持久化 Token
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		// 赋值数据源
		jdbcTokenRepository.setDataSource(dataSource);
		// 自动创建表。第一次执行会创建，以后要执行就要删除掉！
		// jdbcTokenRepository.setCreateTableOnStartup(true);
		return jdbcTokenRepository;
	}
}
