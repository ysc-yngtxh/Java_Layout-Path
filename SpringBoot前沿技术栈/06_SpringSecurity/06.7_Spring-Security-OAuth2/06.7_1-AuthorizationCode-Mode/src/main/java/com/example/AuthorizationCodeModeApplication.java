package com.example;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AuthorizationCodeModeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationCodeModeApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(OAuth2User user) {
        if (user instanceof OidcUser) {
            OidcUser oidcUser = (OidcUser) user;
            return "Hello, " + oidcUser.getFullName() + "! Your email is " + oidcUser.getEmail();
        } else {
            Map<String, Object> attributes = user.getAttributes();
            return "Hello, " + attributes.get("name") + "! Your email is " + attributes.get("email");
        }
    }
}
