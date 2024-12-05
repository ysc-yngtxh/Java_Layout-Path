package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-05 11:54
 * @apiNote TODO
 */
public interface ClientService {

    /**
     * 根据 principalName 查找已授权的应用列表
     *
     * @param principalName 登录用户名
     * @return 已授权的应用List
     */
    List<RegisteredClient> findRegisteredClientsByPrincipalName(String principalName);

    /**
     * 根据
     * clientId 和 principalName 撤销已授权的应用
     *
     * @param clientId      客户端id
     * @param principalName 登录用户名
     */
    void revokeAuthorization(String clientId, String principalName);
}
