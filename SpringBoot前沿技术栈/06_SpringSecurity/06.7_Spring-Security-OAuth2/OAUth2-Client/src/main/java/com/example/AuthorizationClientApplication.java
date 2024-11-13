package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class AuthorizationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationClientApplication.class, args);
    }

    private static final String AUTHORIZE_URL = "https://gitee.com/oauth/authorize";

    private static final String CALLBACK_URI = "http://localhost:8080/callback";

    @RequestMapping("/gitee")
    public String accessToken(String code) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(AUTHORIZE_URL);
        urlBuilder.append("?response_type=code")
                .append("&client_id=").append("8def619da68a212d02a36d471cef229ab3b80c81222e76ed2e581de76f9a6d0a")
                .append("&redirect_uri=").append(URLEncoder.encode(CALLBACK_URI))
                .append("&scope=user_info");
        String urlPath = urlBuilder.toString();
        System.out.println(urlPath);
        URL url = new URL(urlPath);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        // 允许输出流/允许参数
        urlConnection.setDoInput(true);
        if (urlConnection.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String str;
            while ((str = br.readLine()) != null) {
                System.err.println(str);
            }
            br.close();
        }
        return "123";
    }
}
