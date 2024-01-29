package com.michalmlynarczyk.authenticationservice.controller;


import com.michalmlynarczyk.authenticationservice.model.dto.response.UserDetailsResponse;
import com.michalmlynarczyk.authenticationservice.service.UserService;
import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;


    @Override
    public ResponseEntity<UserDetailsResponse> getOrderDetails(final UUID userId, final CustomAuthenticationPrincipal principal) {
        log.debug("getOrderDetails() - userId = {} - principal = {}", userId, principal);
        UserDetailsResponse response = userService.getUserDetails(userId, principal);
        log.debug("getOrderDetails() - response = {}", response);
        return ResponseEntity.ok(response);

    }
}
