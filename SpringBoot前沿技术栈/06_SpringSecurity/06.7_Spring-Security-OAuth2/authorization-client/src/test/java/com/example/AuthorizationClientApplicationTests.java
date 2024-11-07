package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorizationClientApplicationTests {

    private static final String AUTHORIZE_URL = "https://gitee.com/oauth/authorize";

    private static final String CALLBACK_URI = "http://localhost:8080/callback";

    @Test
    void contextLoads() throws IOException {
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
    }

}
