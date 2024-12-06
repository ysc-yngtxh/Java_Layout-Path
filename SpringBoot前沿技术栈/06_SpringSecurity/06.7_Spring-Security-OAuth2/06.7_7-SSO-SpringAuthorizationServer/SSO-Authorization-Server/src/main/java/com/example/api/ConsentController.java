package com.example.api;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-01 22:29
 * @apiNote TODO
 */
@Controller
public class ConsentController {

    @Autowired
    private RegisteredClientRepository registeredClientRepository;

    @GetMapping("/consent")
    public String consent(HttpServletRequest request, Principal principal,
                          @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId,
                          @RequestParam(OAuth2ParameterNames.STATE) String state) {
        // 获取认证用户的name
        String principalName = principal.getName();
        // 根据clientId获取客户端应用
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(clientId);
        // 获取客户端应用名
        String clientName = registeredClient.getClientName();
        // 获取回调地址
        String redirectUri = registeredClient.getRedirectUris().iterator().next();
        // 获取 scopes
        Set<String> scopes = registeredClient.getScopes();

        // 存储 principalName
        request.setAttribute("principalName", principalName);
        // 存储客户端应用名
        request.setAttribute("clientName", clientName);
        // 存储 clientId
        request.setAttribute("clientId", clientId);
        // 存储state
        request.setAttribute("state", state);
        // 存储 scopes
        request.setAttribute("scopes", scopes);

        // 返回 consent.html
        return "consent";
    }
}