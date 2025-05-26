package com.example;

import com.example.common.RPC_URL;
import com.example.protocol.HttpServer;
import com.example.register.LocalRegister;
import com.example.register.MapRemoveRegister;
import com.example.service.HelloService;
import com.example.service.HelloServiceImpl;

public class ProviderApplication3 {

	public static void main(String[] args) {
		// 本地注册具体服务实现类
		LocalRegister.register(HelloService.class.getSimpleName(), "1.0.0", HelloServiceImpl.class);

		// 注册中心注册服务器地址
		RPC_URL RPCUrl = new RPC_URL("localhost", 8080);
		MapRemoveRegister.register(HelloService.class.getSimpleName(), RPCUrl);

		HttpServer httpServer = new HttpServer();
		httpServer.start("localhost", 8080);
	}

}
