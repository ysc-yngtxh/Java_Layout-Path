package com.example.webServer;

import lombok.SneakyThrows;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-01 22:29
 * @apiNote TODO
 */
public class JettyWebServer implements WebServer {

    @SneakyThrows
    @Override
    public void start(WebApplicationContext webApplicationContext) {
        System.out.println("启动Jetty服务器");

        Server server = new Server(8080);
        server.setHandler(new DefaultHandler());
        server.start();
        server.join();
    }
}
