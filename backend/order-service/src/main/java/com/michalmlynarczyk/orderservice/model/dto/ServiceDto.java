package com.michalmlynarczyk.orderservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServiceDto(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        Integer price,
        @NotNull
        Integer quantity
) {
}
