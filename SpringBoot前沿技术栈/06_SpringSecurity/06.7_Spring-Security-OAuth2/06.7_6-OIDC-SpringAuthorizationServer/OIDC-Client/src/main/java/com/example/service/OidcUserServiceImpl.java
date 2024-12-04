package com.example.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-12-04 20:46
 * @apiNote TODO
 */
@Service
public class OidcUserServiceImpl extends OidcUserService {

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        System.out.println("oidcUser = " + oidcUser);

        OidcIdToken idToken = userRequest.getIdToken();

        // 基于不同的 provider 做判断，然后保存用户
        return oidcUser;
    }

}
