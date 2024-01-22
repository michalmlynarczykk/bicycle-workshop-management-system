package com.michalmlynarczyk.workshopmanagementservice.model.dto.response;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.ApplicationStatus;

import java.util.Date;
import java.util.UUID;

public record WorkshopJoinApplicationResponse(

        String id,

        Date createdAt,

        Date decidedAt,

        UUID workshopId,

        UUID userId,

        ApplicationStatus status) {
}
