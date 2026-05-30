package com.example.desafio_java_spring.security;

import com.example.desafio_java_spring.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties props;

    // --- Geração ---

    public String generateAccessToken(User user) {
        return buildToken(user, props.getExpirationMs(), Map.of(
                "role", user.getRole().name(),
                "userId", user.getId().toString()
        ));
    }

    public String generateRefreshToken(User user) {
        return buildToken(user, props.getRefreshExpirationMs(), Map.of());
    }

    private String buildToken(User user, long expirationMs, Map<String, Object> extraClaims) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(now + expirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    // Validação

    public boolean isValid(String token, User user) {
        return extractEmail(token).equals(user.getEmail()) && !isExpired(token);
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(props.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}
