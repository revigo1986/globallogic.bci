package com.globallogic.bci.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static final String SECRET_KEY = "secretKey";

    public static String generateToken(UUID userId, String email) {
        long expirationTime = 1000 * 60 * 60 * 24; // 24h
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
