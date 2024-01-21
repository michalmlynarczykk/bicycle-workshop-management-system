package com.michalmlynarczyk.authenticationservice.model.dto.request;

import com.michalmlynarczyk.common.model.dto.authentication.WorkshopPosition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationRequest(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotNull
        WorkshopPosition workshopPosition) {

    @Override
    public String toString() {
        return "UserRegistrationRequest{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", workshopPosition=" + workshopPosition +
                '}';
    }
}
