package com.michalmlynarczyk.workshopmanagementservice.model.dto.response;

import java.util.List;

public record WorkshopJoinResponseWrapper(
        List<WorkshopJoinResponse> workshopJoinRequests) {
}
