package com.michalmlynarczyk.workshopmanagementservice.mapper;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinApplicationResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.entity.WorkshopJoinApplication;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkshopJoinApplicationMapper {


    public static WorkshopJoinApplicationResponse toDto(final WorkshopJoinApplication workshop) {
        return new WorkshopJoinApplicationResponse(
                workshop.getId(),
                workshop.getCreatedAt(),
                workshop.getDecidedAt(),
                workshop.getWorkshopId(),
                workshop.getUserId(),
                workshop.getStatus()
        );
    }
}
