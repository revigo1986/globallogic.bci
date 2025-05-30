package com.globallogic.bci.security;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    private static final String SECRET_KEY = "secretKey"; // Guárdala bien

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

    public static Claims extractClaims(String token) throws Exception {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String extractEmail(String token) throws Exception {
        return extractClaims(token).getSubject();
    }

    public static UUID extractUserId(String token) throws Exception {
        return UUID.fromString((String) extractClaims(token).get("userId"));
    }
}
