package com.michalmlynarczyk.authenticationservice.model.dto.request;

import com.michalmlynarczyk.common.model.dto.authentication.WorkshopPosition;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationRequest(
        @NotBlank
        @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
        String email,
        @NotBlank
        String password,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
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
