package com.example.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OauthClientController {

    @RequestMapping(path = "/hello")
    public String demo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);

        return "hello";
    }
}