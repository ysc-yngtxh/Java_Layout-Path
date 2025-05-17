package com.example;

import com.example.common.Invocation;
import com.example.protocol.HttpClient;

public class ConsumerApplication {

	public static void main(String[] args) {
		// TODO 先开启Provider，再启动Consumer

		// 包装请求信息
		Invocation invocation = new Invocation(
				"HelloService"
				, "sayHello"
				, new Class[]{String.class}
				, new Object[]{"World"}
				, "1.0.0");

		// 发送请求
		HttpClient client = new HttpClient();
		String res = client.sendRequest("localhost", 8080, invocation);
		System.out.println(res);
	}

}
