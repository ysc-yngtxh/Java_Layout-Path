package com.wen3.oauth.ss.authclient.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationResponseType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tangheng
 */
@UtilityClass
public class OAuth2Utils {

    private static final StringKeyGenerator DEFAULT_STATE_GENERATOR = new Base64StringKeyGenerator(Base64.getUrlEncoder());

    public String getAuthorizationUri(HttpServletRequest request, ClientRegistration clientRegistration) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(OAuth2ParameterNames.RESPONSE_TYPE, OAuth2AuthorizationResponseType.CODE.getValue());
        parameters.put(OAuth2ParameterNames.CLIENT_ID, clientRegistration.getClientId());
        parameters.put(OAuth2ParameterNames.SCOPE, "phone");
        parameters.put(OAuth2ParameterNames.STATE, DEFAULT_STATE_GENERATOR.generateKey());

        // @formatter:off
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath() + "/login/oauth2/code/client-id-2")
                .replaceQuery(null)
                .fragment(null)
                .build();
        // @formatter:on
        parameters.put(OAuth2ParameterNames.REDIRECT_URI, uriComponents.toUriString());

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        parameters.forEach((k, v) -> queryParams.set(UriUtils.encodeQueryParam(k, StandardCharsets.UTF_8), UriUtils.encodeQueryParam(String.valueOf(v), StandardCharsets.UTF_8))); // Encoded

        UriBuilder uriBuilder = new DefaultUriBuilderFactory()
                .uriString(clientRegistration.getProviderDetails().getAuthorizationUri())
                .queryParams(queryParams);
        return uriBuilder.build().toString();
    }
}