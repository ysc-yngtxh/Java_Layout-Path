package com.example.factory.router;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-17 22:30
 * @apiNote TODO Auth自定义路由断言规则
 */
@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {

	public AuthRoutePredicateFactory() {
		super(Config.class);
	}

	@Override
	public Predicate<ServerWebExchange> apply(Config config) {
		return exchange -> {
			// 获取到请求中的所有header
			HttpHeaders headers = exchange.getRequest().getHeaders();
			// 一个请求头可以包含多个值
			List<String> userNameArrays = headers.get(config.userName);
			if (CollectionUtils.isEmpty(userNameArrays)) {
				return false;
			}
			List<String> userNameList = userNameArrays.stream()
			                                          .map(name -> Arrays.asList(name.split(",")))
			                                          .flatMap(Collection::stream)
			                                          .toList();
			// 只要请求头中指定的多个密码值中包含了配置文件中指定的密码，就可以通过
			if (userNameList.contains(config.passWord)) {
				return true;
			}
			return false;
		};
	}

	// 支持断言路由的重写方法
	@Override
	public List<String> shortcutFieldOrder() {
		// 相当于Cookie的展开写法
		return Arrays.asList("userName", "passWord");
	}

	@Data
	public static class Config {
		private String userName;
		private String passWord;
	}
}
