package com.hzc.yuanhuan.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtils {

    private static final String TOKEN_SECRET_KEY = "123456";

    /**
     * 生成Token
     * @param subject   用户名
     * @return token
     */
    public static String createToken(String subject) {
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);
        long TOKEN_EXPIRATION = 24 * 60 * 60 * 1000;
        Date expirationDate = new Date(currentTimeMillis + TOKEN_EXPIRATION);

        //  存放自定义属性，比如用户拥有的权限
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET_KEY)
                .compact();
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(TOKEN_SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static void main(String[] args) {
        String s = extractUsername("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjU1NTMzNDEzLCJpYXQiOjE2NTU0NDcwMTN9.MYhyyLfwVFMDt0O-Y924hs0IUygNJDRTVUhJBq-U-NdvUokfznSMjg6Y7GzyGO-6LOLfZ701KKQlSVPkA8_ebw");
        System.out.println(s);
    }
}
