package com.michalmlynarczyk.orderservice.model.dto;

import jakarta.validation.constraints.NotBlank;

public record BikeDto(
        @NotBlank
        String brand,
        @NotBlank
        String model,
        String color,
        String frameNumber,
        String productionYear,
        String description
) {
}
