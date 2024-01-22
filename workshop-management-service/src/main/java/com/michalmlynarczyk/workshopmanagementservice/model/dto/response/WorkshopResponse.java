package com.michalmlynarczyk.workshopmanagementservice.model.dto.response;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

public record WorkshopResponse(

        UUID id,

        Date createdAt,

        UUID ownerId,

        String name,

        String street,

        String buildingNumber,

        String apartmentNumber,

        String city,

        String zipCode
) {
}
