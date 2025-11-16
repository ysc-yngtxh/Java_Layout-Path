package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.security.Keys;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.crypto.SecretKey;

/**
 * @author 游家纨绔
 * @date 2022-07-06 12:30:00
 * @apiNote
 */
public class JwtUtil {

	// 设置默认密钥（盐值）
	public static final String JWT_SECRET = "qwertyuiopasdfghjklzxcvbnm1234567890";
	// 设置默认头部header
	public static final Map<String, Object> JWT_HEADER = new HashMap<String, Object>() {{
		put("alg", "HS256");
		put("typ", "JWT");
	}};
	// 设置随机唯一标识Id
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	// 设置默认主题
	public static final String JWT_SUBJECT = "游家纨绔";
	// 默认有效期为 1 小时
	public static final Long JWT_TTL = 60 * 60 * 1000L;
	// 设置默认签发者
	public static final String JWT_ISSUER = "曹家千金";
	// 设置默认接收者
	public static final List<String> JWT_AUDIENCE = Arrays.asList("app", "vip", "admin");

	/**
	 * 生成jwt token（负载）
	 */
	public static String createJwt(Map<String, Object> payLoadMap) {
		return getJwtBuilder(null, null, payLoadMap, getUUID(), JWT_SUBJECT, null, JWT_ISSUER, JWT_AUDIENCE).compact();
	}

	/**
	 * 生成jwt token（负载、有效期）
	 */
	public static String createJwt(Map<String, Object> payLoadMap, Long ttlMillis) {
		return getJwtBuilder(null, null, payLoadMap, getUUID(), JWT_SUBJECT, ttlMillis, JWT_ISSUER, JWT_AUDIENCE).compact();
	}

	/**
	 * 生成jwt token（盐值、header、负载、标识Id、主题、有效期、签发者、接收者）
	 */
	public static String createJwt(String secret, Map<String, Object> headerMap, Map<String, Object> payLoadMap,
	                               String id, String subject, Long ttlMillis, String issuer, List<String> audience) {
		return getJwtBuilder(secret, headerMap, payLoadMap, id, subject, ttlMillis, issuer, audience).compact();
	}

	private static JwtBuilder getJwtBuilder(String secret, Map<String, Object> headerMap, Map<String, Object> payLoadMap,
	                                        String id, String subject, Long ttlMillis, String issuer, List<String> audience) {
		SecretKey secretKey = generalKey(secret);
		long nowMillis = System.currentTimeMillis(); // 当前时间毫秒值
		Date now = new Date(nowMillis);         // 当前时间

		if (ttlMillis == null) {
			ttlMillis = JWT_TTL;
		}
		long expMillis = nowMillis + ttlMillis;      // 过期毫秒值：当前时间毫秒值+有效时间毫秒值
		Date expDate = new Date(expMillis);     // 有效时间

		if (Collections.isEmpty(headerMap)) {
			headerMap = JWT_HEADER;
		}

		return Jwts.builder()
		           .header().add(headerMap).and()    // 头部header
		           .claims(payLoadMap)               // 负载
		           .id(id)                        // 唯一标识Id
		           .subject(subject)              // 主题
		           .issuer(issuer)                // 签发者
		           .issuedAt(now)              // 签发时间
		           .expiration(expDate)             // 过期时间
		           .signWith(secretKey)             // 签名算法和密钥
		           .audience().add(audience).and(); // 接收者
	}
	private static SecretKey generalKey(String secret) {
		// 1. 确定使用的密钥
		String keyToUse = Objects.nonNull(secret) ? secret : JWT_SECRET;
		// 2. Base64解码
		byte[] decodedKey = Base64.getDecoder().decode(keyToUse);
		// 3. 验证并确保密钥长度符合要求
		if (decodedKey.length == 0) {
			throw new IllegalArgumentException("解码后的密钥不能为空");
		}
		// JWT 规范要求 HMAC-SHA 算法的密钥至少需要 256 位（32字节），因此当密钥长度不够时做填充处理。
		if (decodedKey.length < 32) {
			// 警告：简单填充不是最佳实践，仅作为后备方案
			System.err.println("警告：密钥长度不足，正在填充到最小长度。建议使用更强的密钥。");
			decodedKey = Arrays.copyOf(decodedKey, 32);
		}
		return Keys.hmacShaKeyFor(decodedKey);
	}

	/**
	 * 解析jwt token
	 */
	public static Claims parseJwt(String secret, String jwt) throws JwtException {
		SecretKey secretKey = generalKey(secret);
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
		HashMap<String, Object> map = new HashMap<String, Object>(){{
			put("username", "user123");
		}};
		String token = createJwt(map);
		System.out.println("Generated Token: " + token);

		try {
			Claims claims = parseJwt(null, token);
			System.out.println("Parsed Claims: " + claims);
			System.out.println("Parsed Claims: " + claims.get("username"));
		} catch (JwtException e) {
			System.err.println("JWT Validation Error: " + e.getMessage());
		}
	}
}
