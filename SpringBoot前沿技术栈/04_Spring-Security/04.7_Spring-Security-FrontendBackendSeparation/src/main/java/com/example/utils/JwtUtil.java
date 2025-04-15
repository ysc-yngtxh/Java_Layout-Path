package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;

/**
 * @author 游诗成
 * @date 2022/07/06
 * @apiNote
 */
public class JwtUtil {

	// 有效期为 1 小时
	public static final Long JWT_TTL = 60 * 60 * 1000L;
	// 设置密钥明文
	public static final String JWT_KEY = "example";

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 生成jwt token
	 */
	public static String createJwt(String subject) {
		return getJwtBuilder(subject, null, getUUID()).compact();
	}

	/**
	 * 生成jwt token
	 */
	public static String createJwt(String subject, Long ttlMillis) {
		return getJwtBuilder(subject, ttlMillis, getUUID()).compact();
	}

	/**
	 * 生成jwt token
	 */
	public static String createJwt(String id, String subject, Long ttlMillis) {
		return getJwtBuilder(subject, ttlMillis, id).compact();
	}

	private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
		SecretKey secretKey = generalKey();
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		if (ttlMillis == null) {
			ttlMillis = JWT_TTL;
		}
		long expMillis = nowMillis + ttlMillis;
		Date expDate = new Date(expMillis);

		return Jwts.builder()
		           .id(uuid)            // 唯一的ID
		           .subject(subject)    // 主题
		           .issuer("ysc")       // 签发者
		           .issuedAt(now)    // 签发时间
		           .expiration(expDate)   // 过期时间
		           .signWith(secretKey);  // 签名算法和密钥
	}

	/**
	 * 生成加密后的密钥
	 */
	private static SecretKey generalKey() {
		byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
		// JWT 规范要求 HMAC-SHA 算法的密钥至少需要 256 位（32字节），因此当密钥长度不够时做填充处理。
		if (encodedKey.length < 32) {
			encodedKey = Arrays.copyOf(encodedKey, 32);
		}
		return Keys.hmacShaKeyFor(encodedKey);
	}

	/**
	 * 解析jwt token
	 */
	public static Claims parseJwt(String jwt) throws JwtException {
		SecretKey secretKey = generalKey();
		return Jwts.parser()
		           .verifyWith(secretKey)
		           .build()
		           .parseSignedClaims(jwt)
		           .getPayload();
	}

	/**
	 * 测试方法
	 */
	public static void main(String[] args) {
		String token = createJwt("user123");
		System.out.println("Generated Token: " + token);

		try {
			Claims claims = parseJwt(token);
			System.out.println("Parsed Claims: " + claims);
		} catch (JwtException e) {
			System.err.println("JWT Validation Error: " + e.getMessage());
		}
	}
}
