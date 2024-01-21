package com.michalmlynarczyk.authenticationservice.util.jwt;

import com.michalmlynarczyk.authenticationservice.model.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenGeneratorImpl implements JwtTokenGenerator {

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Value("${token.expiration}")
    private long tokenExpirationInMilliseconds;


    @Override
    public String generateToken(final User userDetails) {
        final Set<String> permissions = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        final Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userDetails.getId());
        claims.put("permissions", permissions);
        claims.put("roles", userDetails.getRoleNames());

        return generateToken(claims, userDetails.getUsername());
    }


    private String generateToken(final Map<String, Object> claims, final String subject) {
        final Date now = new Date();
        final Date expirationDate = new Date(now.getTime() + tokenExpirationInMilliseconds);
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(getSigningKey())
                .compact();
    }


    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
