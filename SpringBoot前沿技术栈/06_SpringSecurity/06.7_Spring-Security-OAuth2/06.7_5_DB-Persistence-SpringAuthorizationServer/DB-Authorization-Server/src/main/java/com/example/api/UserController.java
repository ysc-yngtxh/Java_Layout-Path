package com.example.api;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-02 23:28
 * @apiNote TODO
 */
@Controller
public class UserController {

    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    // 创建客户端授权应用
    @RequestMapping("/create/app")
    public void createApp(@RequestParam String clientName) {
        RegisteredClient registeredClient = RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientName(clientName)
                .redirectUri("http://localhost:8080/login")
                .build();
        registeredClientRepository.save(registeredClient);
    }
}
