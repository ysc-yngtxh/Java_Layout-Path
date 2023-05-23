package com.wen3.oauth.ss.authclient.handler;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wen3.oauth.ss.authclient.utils.OAuth2Utils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tangheng
 */
@RequiredArgsConstructor
public class DemoBearerTokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ClientRegistrationRepository clientRegistrationRepository;

    private String realmName;

    /**
     * Collect error details from the provided parameters and format according to RFC
     * 6750, specifically {@code error}, {@code error_description}, {@code error_uri}, and
     * {@code scope}.
     * @param request that resulted in an <code>AuthenticationException</code>
     * @param response so that the user agent can begin authentication
     * @param authException that caused the invocation
     */
    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        Map<String, String> parameters = new LinkedHashMap<>();
        if (this.realmName != null) {
            parameters.put("realm", this.realmName);
        }
        if (authException instanceof OAuth2AuthenticationException) {
            OAuth2Error error = ((OAuth2AuthenticationException) authException).getError();
            parameters.put("error", error.getErrorCode());
            if (StringUtils.hasText(error.getDescription())) {
                parameters.put("error_description", error.getDescription());
            }
            if (StringUtils.hasText(error.getUri())) {
                parameters.put("error_uri", error.getUri());
            }
            if (error instanceof BearerTokenError) {
                BearerTokenError bearerTokenError = (BearerTokenError) error;
                if (StringUtils.hasText(bearerTokenError.getScope())) {
                    parameters.put("scope", bearerTokenError.getScope());
                }
                status = ((BearerTokenError) error).getHttpStatus();
            }
        }
        String wwwAuthenticate = computeWWWAuthenticateHeaderValue(parameters);
        response.addHeader(HttpHeaders.WWW_AUTHENTICATE, wwwAuthenticate);
        response.setStatus(status.value());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        JSONObject json = new JSONObject();
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("client-id-1");
        // http://127.0.0.1:8081/oauth2/authorization/client-id-2
//        json.set("url", OAuth2Utils.getAuthorizationUri(request, clientRegistration));
        json.set("url", "http://127.0.0.1:8081/oauth2/authorization/client-id-2");
        json.set("reson", "登录地址");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(json));
    }

    /**
     * Set the default realm name to use in the bearer token error response
     * @param realmName
     */
    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    private static String computeWWWAuthenticateHeaderValue(Map<String, String> parameters) {
        StringBuilder wwwAuthenticate = new StringBuilder();
        wwwAuthenticate.append("Bearer");
        if (!parameters.isEmpty()) {
            wwwAuthenticate.append(" ");
            int i = 0;
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                wwwAuthenticate.append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
                if (i != parameters.size() - 1) {
                    wwwAuthenticate.append(", ");
                }
                i++;
            }
        }
        return wwwAuthenticate.toString();
    }
}