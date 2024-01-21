package com.michalmlynarczyk.common.jwt;

import com.michalmlynarczyk.common.model.dto.auth.CustomAuthenticationPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtTokenUtilImpl implements JwtTokenUtil {

    @Value("${token.signing.key}")
    private String jwtSigningKey;


    @Override
    public boolean isTokenValid(final String token) {
        return !isTokenExpired(token);
    }


    private <T> T extractClaim(final String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(final String token) {
        final SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSigningKey));
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }


    @Override
    public UsernamePasswordAuthenticationToken convertJwtTokenToUsernamePasswordAuthenticationToken(final String token) {
        final Claims claims = extractAllClaims(token);
        final Map<String, Object> claimsMap = new HashMap<>(extractAllClaims(token));
        final Collection<String> permissions = (Collection<String>) claimsMap.get("permissions");

        final Collection<? extends GrantedAuthority> authorities = permissions
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new UsernamePasswordAuthenticationToken(
                new CustomAuthenticationPrincipal(UUID.fromString(claims.get("userId", String.class)), claims.getSubject()),
                "N/A",
                authorities
        );
    }
}
