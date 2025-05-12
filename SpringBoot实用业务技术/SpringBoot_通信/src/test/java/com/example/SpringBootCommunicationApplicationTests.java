package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootCommunicationApplicationTests {

	// 在Jdk中，主要有两种方式实现HTTP通信：
	//     1、使用 HttpURLConnection（Jdk自带）。
	//     2、使用 HttpClient（Jdk 11 引入的新API）。
	// 使用第三方库通信：
	//     1、使用 OkHttp。（更智能的连接复用机制）
	//     2、使用 Apache HttpClient。
	//     性能对比：                 OkHttp              Apache HttpClient
	//            连接复用       更智能的连接复用机制          需要手动配置优化
	//            内存占用             较低                      较高
	//            高并发性能      优秀(尤其HTTP/2)             良好(需要调优)
	//            启动速度             更快                  较慢(初始化复杂)
	//     OkHttp > Apache HttpClient > HttpClient > HttpURLConnection
	// 其它通信方式：
	//     1、使用 WebSocket
	//     2、使用 Spring Boot RestTemplate（Spring Boot自带，推荐方式）。
	//        在 Spring Boot 中，RestTemplate 的底层通信实现取决于配置的 ClientHttpRequestFactory
	//        默认情况下，RestTemplate 使用 SimpleClientHttpRequestFactory，该工厂基于 Jdk 原生的 HttpURLConnection 发起 HTTP 请求
	//        用户可通过自定义 ClientHttpRequestFactory 切换底层 HTTP 客户端库


	// 请求体
	final String OUTPUT_JSON_STRING = """
			{
			  "title": "foo",
			  "body": "bar",
			  "userId": 1
			}
			""";


	@Test
	void URLConnectionTest() throws IOException, URISyntaxException {
		// URLConnection优点：
		//     内置类，无需添加依赖。
		//     易于实现简单的HTTP请求。
		// URLConnection缺点：
		//     使用较复杂，代码冗长。
		//     不支持异步。
		//     不便于维护和扩展。

		// TODO GET请求
		String urlGetStr = "https://jsonplaceholder.typicode.com/posts/1";
		URL getUrl = new URI(urlGetStr).toURL();
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.setRequestMethod("GET");
		// 设置请求头
		connection.setRequestProperty("User-Agent", "Mozilla/5.0");
		// 获取响应内容
		getResponseContent("GET", connection);

		// TODO POST请求
		String urlPostStr = "https://jsonplaceholder.typicode.com/posts";
		URL postUrl = new URI(urlPostStr).toURL();
		HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
		postConnection.setRequestMethod("POST");
		// 设置请求头
		postConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		postConnection.setRequestProperty("Accept", "application/json");
		// 用于设置HTTP请求是否允许向服务器发送数据
		postConnection.setDoOutput(true);
		// 发送数据
		try (OutputStream os = postConnection.getOutputStream()) {
			byte[] input = OUTPUT_JSON_STRING.getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);
		}
		// 获取响应内容
		getResponseContent("POST", postConnection);
	}

	private static void getResponseContent(String requestType, HttpURLConnection connection) throws IOException {
		// 检查响应码
		int responseCode = connection.getResponseCode();
		System.out.println(requestType + " Response Code: " + responseCode);
		// 读取响应
		if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder strBuilder = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				strBuilder.append(inputLine);
			}
			in.close();

			System.out.println(requestType + " Response: " + strBuilder);
		} else {
			System.out.println(requestType + " request failed.");
		}
	}

	@Test
	void HttpClientTest() {
		// HttpClient优点：
		//     简洁且现代化的API。
		//     支持同步和异步。
		//     支持HTTP/2。
		// HttpClient缺点：
		//     需要 JDK 11+ 支持。
		try (HttpClient client = HttpClient.newHttpClient()) { // 创建 HttpClient 实例
			// TODO 创建 HttpRequest 实例（GET请求）
			HttpRequest getRequest =
					HttpRequest.newBuilder()
					           .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
					           .GET()
					           .build();
			// 发送请求并获取响应
			HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());
			System.out.println("GET Response Code: " + response.statusCode());
			System.out.println("GET Response Body: " + response.body());

			// TODO 创建 HttpRequest 实例（POST请求）
			HttpRequest postRequest =
					HttpRequest.newBuilder()
					           .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
					           .header("Content-Type", "application/json")
					           .POST(HttpRequest.BodyPublishers.ofString(OUTPUT_JSON_STRING))
					           .build();

			// TODO 异步请求
			CompletableFuture<HttpResponse<String>> responseFuture =
					client.sendAsync(postRequest, HttpResponse.BodyHandlers.ofString());
			// 这里的 join()是一个阻塞方法，调用这个方法，可以使当前线程（通常是主线程）等待
			// 直到responseFuture完成，避免程序过早退出（单元测试和多异步任务结果合并时常用join方法）
			responseFuture.thenAccept(resp -> {
				System.out.println("POST Response Code: " + resp.statusCode());
				System.out.println("POST Response Body: " + resp.body());
			}).join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void OkHttpClientTest() throws IOException {
		// OkHttpClient okHttpClient = new OkHttpClient();
		// OkHttpClient调优
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.connectionPool(new ConnectionPool(50, 5, TimeUnit.MINUTES))
				.connectTimeout(10, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.build();
		Request request = new Request.Builder()
				                     .url("https://jsonplaceholder.typicode.com/posts/1")
				                     .get()   // 默认就是GET请求，可以不写
				                     .build();
		try (Response response = okHttpClient.newCall(request).execute()) {
			String string = response.body().string();
			System.out.println(string);
		}
	}
}
