package com.michalmlynarczyk.authenticationservice.controller;

import com.michalmlynarczyk.authenticationservice.model.dto.request.UserLoginRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.request.UserRegistrationRequest;
import com.michalmlynarczyk.authenticationservice.model.dto.response.JwtTokenResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Authentication Controller", description = "Endpoints for login and registration")
@RequestMapping("/external/v1/auth")
public interface AuthenticationController {

    @PostMapping("/login")
    ResponseEntity<JwtTokenResponse> login(@RequestBody @Valid final UserLoginRequest request);

    @PostMapping("/register")
    ResponseEntity<JwtTokenResponse> register(@RequestBody @Valid final UserRegistrationRequest request);
}
