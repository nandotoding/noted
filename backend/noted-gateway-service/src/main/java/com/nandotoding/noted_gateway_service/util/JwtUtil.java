package com.nandotoding.noted_gateway_service.util;

import com.nandotoding.noted_gateway_service.exception.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${jwt.secretkey}")
    private String jwtSecret;

    public boolean validateToken(String token) {
        if (token == null) {
            throw new UnauthorizedException("Token is null");
        }

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (RuntimeException e) {
            throw new UnauthorizedException("Invalid JWT");
        }
    }

    public String extractAccountId(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
