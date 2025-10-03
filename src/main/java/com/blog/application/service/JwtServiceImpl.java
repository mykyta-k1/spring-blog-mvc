package com.blog.application.service;

import com.blog.application.contract.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private final String secret;
    @Value("${jwt.expiration}")
    private final long expirationTime;
    private final SecretKey secretKey;

    public JwtServiceImpl(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.expiration}") long expirationTime
    ) {
        this.secret = secret;
        this.expirationTime = expirationTime;
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateToken(UUID userId) {
        return Jwts.builder()
            .subject(userId.toString())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expirationTime))
            .signWith(secretKey)
            .compact();
    }

    @Override
    public UUID pullsOutUserIdFromToken(String token)
        throws ExpiredJwtException, SignatureException, MalformedJwtException {
        return UUID.fromString(Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject());
    }
}
