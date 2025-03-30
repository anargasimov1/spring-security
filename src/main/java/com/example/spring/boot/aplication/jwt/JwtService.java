package com.example.spring.boot.aplication.jwt;

import com.example.spring.boot.aplication.Models.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    @Value("${secretKey}")
    private String secretKey;

    public String generateToken(UserEntity user) {
        Map<String,Object> claims = new HashMap<>();
        claims.put("email",user.getEmail());
        claims.put("role",user.getRole());
        return Jwts.builder()
                .claims(claims)
                .subject(user.getEmail())
                .signWith(getSigningKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+(1000*60*60*24)))
                .compact();


    }


    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
