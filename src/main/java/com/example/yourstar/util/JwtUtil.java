package com.example.yourstar.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {


    @Value("${jwt.secretKey}")
    String secretKey;
    private static Long expriedTime = 1000 * 60 * 24 * 7l; // 만료 시간 (일주일)
    public  String generateToken(String userId) {
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(Date.from(Instant.now().plus(expriedTime, ChronoUnit.MILLIS))) // 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }

    public  Claims parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
