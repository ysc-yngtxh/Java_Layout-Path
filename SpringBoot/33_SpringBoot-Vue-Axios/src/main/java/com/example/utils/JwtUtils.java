package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    private static final long EXPIRE_TIME = 7*24*60*60*1000; // Token存活时间:七天

    private static final String salt = "csaydgcaisdyc";

    /**
     * 生成Token
     */
    public static String getJwtsToken(String username,String password){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // Header
                .setHeaderParam("tpy", "JWT")
                .setHeaderParam("alg", "HS256")
                // payload
                .claim("name",username)
                .claim("pwd",password)
                .setSubject("admin-test")  // 设置主题
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))  // 设置有效时间
                // signature
                .signWith(SignatureAlgorithm.HS256 , salt)
                .compact(); // 将所有信息紧凑起来
        return jwtToken;
    }

    /**
     * 判断token是否过期
     */
    public static boolean isExpire(String token){
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(salt).parseClaimsJws(token);
        Date expiration = claimsJws.getBody().getExpiration();
        int i = expiration.compareTo(new Date(System.currentTimeMillis()));

        if (i >= 0) {
            return true; // token没过期
        }
        return false;
    }

    /**
     * 在token中获取到username信息
     */
    public static String getUsername(String token) {
        try {
            JwtParser jwtParser = Jwts.parser();
            Jws<Claims> claimsJws = jwtParser.setSigningKey(salt).parseClaimsJws(token);
            return claimsJws.getBody().get("name").toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 在token中获取到password信息
     */
    public static String getPassword(String token) {
        try {
            JwtParser jwtParser = Jwts.parser();
            Jws<Claims> claimsJws = jwtParser.setSigningKey(salt).parseClaimsJws(token);
            return claimsJws.getBody().get("pwd").toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 校验Token
     */
    public static boolean verifyToken(String token,String pwd){
        try {
            JwtParser jwtParser = Jwts.parser();
            Jws<Claims> claimsJws = jwtParser.setSigningKey(salt).parseClaimsJws(token);
            String tpy = claimsJws.getHeader().get("tpy").toString();
            String alg = claimsJws.getHeader().get("alg").toString();
            String username = (String) claimsJws.getBody().get("name");
            String password = (String) claimsJws.getBody().get("pwd");
            // 判断token是否过期，  头部类型、算法，   载荷信息是否正确
            if (isExpire(token) && tpy.equals("JWT") && alg.equals("HS256") && pwd.equals(password)) {
                return true;
            }
            return false;
        } catch(Exception e){
            return false;
        }
    }
}
