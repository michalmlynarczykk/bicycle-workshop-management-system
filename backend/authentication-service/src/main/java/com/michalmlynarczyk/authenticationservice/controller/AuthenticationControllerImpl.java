package com.michalmlynarczyk.authenticationservice.controller;

import com.michalmlynarczyk.authenticationservice.model.dto.request.UserLoginRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserRegistrationRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.response.JwtTokenResponse;
import com.michalmlynarczyk.authenticationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final UserService userService;


    @Override
    public ResponseEntity<JwtTokenResponse> login(final UserLoginRequest request) {
        log.debug("login() - enter - request = {}", request);
        final JwtTokenResponse response = userService.login(request);
        return ResponseEntity.ok().body(response);
    }


    @Override
    public ResponseEntity<JwtTokenResponse> register(final UserRegistrationRequest request) {
        log.debug("register() - enter - request = {}", request);
        final JwtTokenResponse response = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
