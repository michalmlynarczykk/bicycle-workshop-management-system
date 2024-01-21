package com.michalmlynarczyk.workshopmanagementservice.model.dto.response;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.JoinRequestStatus;

import java.util.Date;
import java.util.UUID;

public record WorkshopJoinResponse(

        String id,

        Date createdAt,

        Date decidedAt,

        UUID workshopId,

        UUID userId,

        JoinRequestStatus status) {
}
