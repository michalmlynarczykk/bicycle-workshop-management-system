package com.michalmlynarczyk.authenticationservice.model.dto.request;


import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank
        String email,
        @NotBlank
        String password) {
    @Override
    public String toString() {
        return "UserLoginRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}
