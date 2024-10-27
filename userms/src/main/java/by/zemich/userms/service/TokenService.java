package by.zemich.userms.service;

import by.zemich.userms.config.SecureProperties;
import by.zemich.userms.dao.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final SecureProperties secureProperties;

    public String generate(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                // выдаём на сутки
                .expiration(new Date(System.currentTimeMillis() + 86_400_000))
                .signWith(SignatureAlgorithm.HS256, secureProperties.getKey())
                .compact();

    }
}
