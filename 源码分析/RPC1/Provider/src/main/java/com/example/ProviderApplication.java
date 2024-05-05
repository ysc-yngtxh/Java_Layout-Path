package com.example;

import com.example.protocol.HttpServer;
import com.example.register.LocalRegister;
import com.example.service.HelloService;
import com.example.service.HelloServiceImpl;

public class ProviderApplication {

    public static void main(String[] args) {
        // 本地注册具体服务实现类
        LocalRegister.register(HelloService.class.getSimpleName(), "1.0.0",  HelloServiceImpl.class);

        HttpServer httpServer = new HttpServer();
        httpServer.start("localhost", 8080);
    }

}
