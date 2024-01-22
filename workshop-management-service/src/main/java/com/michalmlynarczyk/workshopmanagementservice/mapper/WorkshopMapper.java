package com.michalmlynarczyk.workshopmanagementservice.mapper;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.CreateWorkshopRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.entity.Address;
import com.michalmlynarczyk.workshopmanagementservice.model.entity.Workshop;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WorkshopMapper {

    public static Workshop toEntity(final CreateWorkshopRequest createWorkshopRequest, final UUID ownerId) {
        return Workshop.builder()
                .id(UUID.randomUUID())
                .createdAt(Date.from(OffsetDateTime.now().toInstant()))
                .ownerId(ownerId)
                .name(createWorkshopRequest.name())
                .address(new Address(
                        createWorkshopRequest.street(),
                        createWorkshopRequest.buildingNumber(),
                        createWorkshopRequest.apartmentNumber(),
                        createWorkshopRequest.city(),
                        createWorkshopRequest.zipCode()
                ))
                .build();
    }


    public static WorkshopResponse toDto(final Workshop workshop) {
        return new WorkshopResponse(
                workshop.getId(),
                workshop.getCreatedAt(),
                workshop.getOwnerId(),
                workshop.getName(),
                workshop.getAddress().getStreet(),
                workshop.getAddress().getBuildingNumber(),
                workshop.getAddress().getApartmentNumber(),
                workshop.getAddress().getCity(),
                workshop.getAddress().getZipCode());
    }
}
