package com.michalmlynarczyk.common.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface JwtTokenUtil {

    boolean isTokenValid(final String token);

    UsernamePasswordAuthenticationToken convertJwtTokenToUsernamePasswordAuthenticationToken(final String token);
}
