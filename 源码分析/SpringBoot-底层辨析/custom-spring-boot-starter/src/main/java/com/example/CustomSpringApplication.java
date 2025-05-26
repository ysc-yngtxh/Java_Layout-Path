package com.example;

import com.example.webServer.WebServer;
import java.util.Map;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-01 21:30:00
 * @apiNote TODO
 */
public class CustomSpringApplication {

	public static void run(Class<?> clazz, String... args) {
		// 创建 Spring 容器
		AnnotationConfigWebApplicationContext applicationContext =
				new AnnotationConfigWebApplicationContext();
		applicationContext.register(clazz);
		applicationContext.refresh();

		// 启动 Web 服务器（Tomcat、Jetty...）
		WebServer webServer = getWebServer(applicationContext);
		webServer.start(applicationContext);
	}

	private static WebServer getWebServer(WebApplicationContext webApplicationContext) {
		Map<String, WebServer> webServerMap = webApplicationContext.getBeansOfType(WebServer.class);

		if (webServerMap.isEmpty()) {
			throw new NullPointerException("No WebServer bean found");
		}
		if (webServerMap.size() > 1) {
			throw new IllegalStateException("Multiple WebServer beans found");
		}

		return webServerMap.values().stream().findFirst().get();
	}

}
