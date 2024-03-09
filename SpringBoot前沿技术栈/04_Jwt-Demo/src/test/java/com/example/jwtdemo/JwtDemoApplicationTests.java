package com.example.jwtdemo;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;



@Slf4j
@SpringBootTest
class JwtDemoApplicationTests {

    private String signature = "csaydgcaisdyc"; // 加盐


    @Test
    void contextLoads() {
        // 获取jwt对象
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // Header
                .setHeaderParam("tpy", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload
                .claim("name" ,"陈玉梅")
                .claim("pwd", "514")
                .setSubject("admin-test")  // 设置主题
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24*7))  // 设置有效时间
                .setId(UUID.randomUUID().toString())
                // signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact(); // 将所有信息紧凑起来

        log.info(jwtToken);
    }


    @Test
    void parse() {

        String token = "eyJ0cHkiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi6ZmI546J5qKFIiwicHdkIjoiNTE0Iiwic3ViIjoiYWRtaW4tdGVzdCIsImV4cCI6MTYzNTM1MDE4NywianRpIjoiZjk1NjQ5ZmQtOGZlMS00NjMzLWJmNjctMTgyODNjZDkxNmUxIn0._yGitIWtGZ2LyD2wXIUGKNPqqqneWNxoVk8kdOtWdn0";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey("csaydgcaisdyc").parseClaimsJws(token);
        claimsJws.getHeader().get("tpy");
        System.out.println(claimsJws.getHeader().get("tpy"));
        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("name"));
        System.out.println(claims.get("pwd"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(claims.getExpiration()));
    }

    @Test
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("514","MD5",1024);
        System.out.println(md5Hash);
    }
}
