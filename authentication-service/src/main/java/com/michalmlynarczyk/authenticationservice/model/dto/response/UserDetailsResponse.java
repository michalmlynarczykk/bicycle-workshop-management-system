package com.michalmlynarczyk.authenticationservice.model.dto.response;

import com.michalmlynarczyk.authenticationservice.model.WorkshopPosition;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record UserDetailsResponse(
        UUID id,
        String firstName,
        String lastName,
        String email,
        OffsetDateTime createdAt,
        OffsetDateTime workshopAssignedAt,
        List<WorkshopPosition> positions) {
}
