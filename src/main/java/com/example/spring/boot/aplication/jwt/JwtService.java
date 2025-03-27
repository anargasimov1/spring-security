package com.example.spring.boot.aplication.jwt;

import com.example.spring.boot.aplication.Models.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    public String generateToken(UserEntity user) {
        Map<String,String> claims = new HashMap<>();
        claims.put("name",user.getName());
        claims.put("email",user.getEmail());
        return Jwts.builder()
                .claims(claims)
                .subject("admin")
                .signWith(getSigningKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .compact();


    }

    private Key getSigningKey() {
        String secretKey = "3xv4W92v8bHV92kdoZm5Df5N2P1v9R2z3xJrPqA5H0A=";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
