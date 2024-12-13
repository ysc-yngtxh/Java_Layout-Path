package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author 游诗成
 * @date 2022/07/06
 * @apiNote
 */
public class JwtUtil {

    // 有效期为
    public static final Long JWT_TTL = 60 * 60 * 1000L;
    //设置密钥明文
    public static final String JWT_KEY = "example";

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成jwt token
     */
    public static String createJwt(String Subject) {
        JwtBuilder builder = getJwtBuilder(Subject, null, getUUID()); //设置过期时间
        return builder.compact();
    }

    /**
     * 生成jwt token
     */
    public static String createJwt(String Subject, Long ttlMilis) {
        JwtBuilder builder = getJwtBuilder(Subject, ttlMilis, getUUID()); //设置过期时间
        return builder.compact();
    }

    /**
     * 生成jwt token
     */
    public static String createJwt(String id, String Subject, Long ttlMilis) {
        JwtBuilder builder = getJwtBuilder(Subject, ttlMilis, id); //设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String Subject, Long ttlMilis, String uuid) {
        SignatureAlgorithm sigbatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secreKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMilis == null) {
            ttlMilis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMilis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)         //唯一的Id
                .setSubject(Subject)  //主题
                .setIssuer("ysc")    //签发者
                .setIssuedAt(now)     //签发时间
                .signWith(sigbatureAlgorithm, secreKey)  //签名算法和密钥
                .setExpiration(expDate);  //过期时间
    }

    /**
     * 生成加密后的密钥
     */
    private static SecretKey generalKey() {
        byte[] encodeKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKeySpec key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }

    /**
     * 解析jwt token
     */
    public static Claims parseJwt(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
