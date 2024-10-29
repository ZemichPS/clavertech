package by.zemich.newsms.core.service;

import by.zemich.newsms.api.config.properties.JWTProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


@RequiredArgsConstructor
public class JWTHandler {
    private final JWTProperty jwtProperty;
    private final SecretKey secretKey = Keys.hmacShaKeyFor(jwtProperty.getSecret().getBytes(StandardCharsets.UTF_8));

    public String getUserName(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            throw new IllegalArgumentException("Invalid JWT signature", e);
        }
    }
}
