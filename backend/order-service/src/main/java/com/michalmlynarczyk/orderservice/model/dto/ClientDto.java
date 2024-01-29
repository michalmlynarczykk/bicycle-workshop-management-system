package com.michalmlynarczyk.orderservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientDto(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
        String email) {
}
