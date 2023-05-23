package com.wen3.oauth.ss.authclient.processor;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Collections;

/**
 * @author tangheng
 */
@Configuration
public class SpringOpaqueTokenIntrospectorPostProcessor implements ObjectPostProcessor<SpringOpaqueTokenIntrospector> {

    @Resource
    private OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

    @Override
    public SpringOpaqueTokenIntrospector postProcess(SpringOpaqueTokenIntrospector object) {
        Converter<String, RequestEntity<?>> requestEntityConverter = (token) -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("token", token);

            return new RequestEntity<>(body, headers, HttpMethod.POST, URI.create(oAuth2ResourceServerProperties.getOpaquetoken().getIntrospectionUri()));
        };

        object.setRequestEntityConverter(requestEntityConverter);
        return object;
    }
}
