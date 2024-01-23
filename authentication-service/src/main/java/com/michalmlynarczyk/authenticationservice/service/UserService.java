package com.michalmlynarczyk.authenticationservice.service;

import com.michalmlynarczyk.authenticationservice.model.dto.request.UserLoginRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserRegistrationRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.response.JwtTokenResponse;

import java.util.UUID;

public interface UserService {

    JwtTokenResponse login(final UserLoginRequest request);

    JwtTokenResponse register(final UserRegistrationRequest request);

    void assignWorkshopAndUpgradeRole(final UUID userId, final UUID workshopId);
}
