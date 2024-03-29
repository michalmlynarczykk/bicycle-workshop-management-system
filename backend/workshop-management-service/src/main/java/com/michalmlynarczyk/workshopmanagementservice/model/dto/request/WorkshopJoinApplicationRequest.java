package com.michalmlynarczyk.workshopmanagementservice.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WorkshopJoinApplicationRequest(
        @NotNull
        UUID workshopId
) {
}
