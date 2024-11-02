package by.zemich.newsms.core.utils;

import by.zemich.newsms.config.properties.JWTProperty;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;


@RequiredArgsConstructor
public class JWTHandler {
    private final JWTProperty jwtProperty;
    private SecretKey secretKey;

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

    @PostConstruct
    private void init(){
        secretKey = Keys.hmacShaKeyFor(jwtProperty.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}
