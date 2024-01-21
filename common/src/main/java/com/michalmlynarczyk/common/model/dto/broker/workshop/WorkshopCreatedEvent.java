package com.michalmlynarczyk.common.model.dto.broker.workshop;

import java.util.UUID;

public record WorkshopCreatedEvent(
        UUID workshopId,
        UUID ownerId) {
}
