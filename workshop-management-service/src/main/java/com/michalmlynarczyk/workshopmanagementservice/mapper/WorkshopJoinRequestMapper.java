package com.michalmlynarczyk.workshopmanagementservice.mapper;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.entity.WorkshopJoin;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkshopJoinRequestMapper {


    public static WorkshopJoinResponse toDto(final WorkshopJoin workshop) {
        return new WorkshopJoinResponse(
                workshop.getId(),
                workshop.getCreatedAt(),
                workshop.getDecidedAt(),
                workshop.getWorkshopId(),
                workshop.getUserId(),
                workshop.getStatus()
        );
    }
}
