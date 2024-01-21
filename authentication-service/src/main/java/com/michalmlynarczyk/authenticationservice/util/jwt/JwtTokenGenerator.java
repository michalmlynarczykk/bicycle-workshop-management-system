package com.michalmlynarczyk.authenticationservice.util.jwt;

import com.michalmlynarczyk.authenticationservice.model.entity.User;

public interface JwtTokenGenerator {

    String generateToken(final User userDetails);

}
