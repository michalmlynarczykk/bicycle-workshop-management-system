package com.michalmlynarczyk.workshopmanagementservice.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateWorkshopRequest(

        @NotBlank
        String name,

        String street,

        @NotBlank
        String buildingNumber,

        String apartmentNumber,

        @NotBlank
        String city,

        @NotBlank
        String zipCode) {
}
