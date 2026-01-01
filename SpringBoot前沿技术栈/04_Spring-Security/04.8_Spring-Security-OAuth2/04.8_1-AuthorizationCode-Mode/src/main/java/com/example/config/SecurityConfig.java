package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-23 18:40
 * @apiNote TODO 配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(
				User.withUsername("admin").password("admin").authorities("admin").build(),
				User.withUsername("manager").password("manager").authorities("manager").build());
		return userDetailsManager;
	}

	/**
	 * 使用 Lambda DSL
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// TODO 注意：更新springboot版本3.1.0后。已经不建议继续使用.csrf().disable()、and()这种类似写法（过时了）
				/**
				 * 一、使用 Lambda DSL
				 *    Lambda DSL 是配置 Spring 安全性的首选方法，先前的配置样式在需要使用 Lambda DSL 的 Spring Security 7 中无效。
				 *    这样做主要有几个原因：
				 *    1、以前的方式是不清楚正在配置什么对象，而不知道返回类型是什么。嵌套越深，就越混乱。
				 *       即使是有经验的用户也会认为他们的配置在做一件事，而实际上，它正在做另一件事。
				 *    2、一致性。 许多代码库在两种样式之间切换，这会导致不一致，使理解配置变得困难，并经常导致配置错误。
				 *
				 * 二、Lambda DSL 配置提示
				 *    在 Lambda DSL 中，无需使用该方法链接配置选项。 调用 lambda 方法后，将自动返回实例以进行进一步配置。
				 *    Customizer.withDefaults()使用 Spring 安全性提供的默认值启用安全功能。
				 *
				 * 三、Lambda DSL 的目标
				 *    创建 Lambda DSL 是为了实现以下目标：
				 *       1、自动缩进使配置更具可读性。
				 *       2、无需使用.and()
				 *       3、Spring Security DSL具有与其他Spring DSL类似的配置风格，
				 *          例如Spring Integration和Spring Cloud Gateway。
				 */
				.csrf(AbstractHttpConfigurer::disable)
				.cors(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize -> authorize
						                       .requestMatchers("/").permitAll()
						                       .requestMatchers("/gitee/auth").permitAll()
						                       .requestMatchers("/github/auth").permitAll()
						                       .requestMatchers("/google/auth").permitAll()
						                       .requestMatchers("/login/oauth2/code/*").permitAll()
						                       .requestMatchers("/static/**").permitAll()
						                       .anyRequest().authenticated()
				                      )
				.formLogin(Customizer.withDefaults());
		// .formLogin((formLogin) ->
		//         formLogin
		//                 .usernameParameter("username")
		//                 .passwordParameter("password")
		//                 .loginPage("/authentication/login")
		//                 .failureUrl("/authentication/login?failed")
		//                 .loginProcessingUrl("/authentication/login/process"));
		return http.build();
	}
}
