package com.nandotoding.noted_account_service.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class JwtUtil {
    @Value("${jwt.secretkey}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExp;

    public String generateToken(String id) {
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExp))
                .compact();
    }
}
