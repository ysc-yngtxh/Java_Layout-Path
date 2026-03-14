package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;

// @SpringBootTest
class SpringBootFopApplicationTests {

    @Test
    void contextLoads() throws IOException, URISyntaxException {
        ClassPathResource classPathResource = new ClassPathResource("static/logo.png");
        try (InputStream in = classPathResource.getInputStream()) {
            byte[] bytes = in.readAllBytes();
            String base64 = Base64.getEncoder().encodeToString(bytes);
            String dataUrl = "data:image/png;base64," + base64;
            System.out.println(dataUrl);
        }
    }

}
