package dev.muliroz.backend.infrastructure.security;

import dev.muliroz.backend.domain.gateway.TokenGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtGenerator implements TokenGenerator {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.validity}")
    private long jwtValidity;

    @Override
    public String generate(String email) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .subject(email)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + jwtValidity))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean validateToken(String token, String subject) {
        final String tokenSub = getSubjectFromToken(token);
        return tokenSub.equals(subject) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        final Date tokenExp = getClaimFromToken(token, Claims::getExpiration);
        return tokenExp.before(new Date(System.currentTimeMillis()));
    }

    public String getSubjectFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
