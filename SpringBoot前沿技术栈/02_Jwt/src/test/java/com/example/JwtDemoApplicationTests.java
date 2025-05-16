package com.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class JwtDemoApplicationTests {

	private final String signature = "csaydgcaisdyc"; // 加盐

	@Test
	void contextLoads() {
		// 生成密钥【JWT 规范要求 HMAC-SHA 算法的密钥至少需要 256 位（32字节）】
		// 方案一：填充到满足要求的最小长度
		// 方案二：使用 JJWT 提供的密钥生成器（Jwts.SIG.HS256.key().build()）
		SecretKey key = Keys.hmacShaKeyFor(Arrays.copyOf(signature.getBytes(StandardCharsets.UTF_8), 32));

		// 构建JWT
		String jwtToken = Jwts.builder()
		                      // Header
		                      .header()
		                      .add("tpy", "JWT")
		                      .add("alg", "HS256")
		                      .and()
		                      // Payload
		                      .claims()
		                      .add("name", "陈麻麻")
		                      .add("pwd", "514")
		                      .subject("admin-test")  // 设置主题
		                      .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))  // 设置有效时间
		                      .id(UUID.randomUUID().toString())
		                      .and()
		                      // Signature
		                      .signWith(key)
		                      .compact();

		log.info("生成的JWT Token: {}", jwtToken);
	}

	@Test
	void parse() {
		String token = "eyJ0cHkiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi6ZmI6bq76bq7IiwicHdkIjoiNTE0Iiwic3ViIjoiYWRtaW4tdGVzdCIsImV4cCI6MTc0ODAwNzQwMCwianRpIjoiOGNjZTAxOTktNWE5My00M2ZmLTkwZTgtZThlYTc4MDFlN2U4In0.pv0_TuntJp4zBoP87_klHfbWDSUQZ8fTpL2Zw_sYiA4";

		// 生成密钥【JWT 规范要求 HMAC-SHA 算法的密钥至少需要 256 位（32字节）】
		//         // 方案一：填充到满足要求的最小长度
		//         // 方案二：使用 JJWT 提供的密钥生成器（Jwts.SIG.HS256.key().build()）
		SecretKey key = Keys.hmacShaKeyFor(Arrays.copyOf(signature.getBytes(StandardCharsets.UTF_8), 32));

		// 解析JWT
		Jws<Claims> claimsJws = Jwts.parser()
		                            .verifyWith(key)
		                            .build()
		                            .parseSignedClaims(token);

		// 获取Header信息
		JwsHeader header = claimsJws.getHeader();
		System.out.println("Header tpy: " + header.get("tpy"));
		System.out.println("Header alg: " + header.get("alg"));

		// 获取Payload信息
		Claims claims = claimsJws.getPayload();
		System.out.println("Name: " + claims.get("name"));
		System.out.println("Password: " + claims.get("pwd"));
		System.out.println("JWT ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());

		log.info("Expiration: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(claims.getExpiration()));
	}
}
