package com.michalmlynarczyk.authenticationservice.controller;

import com.michalmlynarczyk.authenticationservice.model.dto.response.UserDetailsResponse;
import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

;

@Tag(name = "Users Controller", description = "Endpoints for users management")
@RequestMapping("/external/v1/users")
public interface UserController {

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('AUTHENTICATION_GET_WORKSHOP_USER_DETAILS')")
    ResponseEntity<UserDetailsResponse> getOrderDetails(@PathVariable(name = "userId") final UUID userId,
                                                        @AuthenticationPrincipal final CustomAuthenticationPrincipal principal);

}
