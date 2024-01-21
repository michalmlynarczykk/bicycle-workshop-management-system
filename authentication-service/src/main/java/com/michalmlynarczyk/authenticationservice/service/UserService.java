package com.michalmlynarczyk.authenticationservice.service;

import com.michalmlynarczyk.authenticationservice.model.dto.request.UserLoginRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserRegistrationRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.response.JwtTokenResponse;

public interface UserService {

    JwtTokenResponse login(final UserLoginRequest request);

    JwtTokenResponse register(final UserRegistrationRequest request);
}
