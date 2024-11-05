package com.example.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-03 09:01
 * @apiNote TODO
 */
@RestController
public class Oath2Controller {

    @RequestMapping("/oauth2/callback")
    public String Oath2AuthorizationCallback(@RequestParam("code") String code) {
        try {
            accessToken(code);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Callback Success!!!" + code;
    }

    public String accessToken(String code) throws IOException {
        String path = "https://github.com/login/oauth/access_token";
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("client_id", "Ov23liukOOM7knKdMlD8");
        urlConnection.setRequestProperty("client_secret", "9a9c059d953cb3043d751c155446e2b6d20afda5");
        urlConnection.setRequestProperty("code", code);
        urlConnection.setRequestMethod("GET");

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

    public void userInfo(String code) throws IOException {
        String path = "https://api.github.com/user";
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Authorization", "token " + code);

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
