package com.michalmlynarczyk.common.model.dto.broker.workshop;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record WorkshopJoinApplicationApprovedEvent(
        @NotNull
        UUID workshopId,
        @NotNull
        UUID userId) {
}
