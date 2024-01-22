package com.michalmlynarczyk.workshopmanagementservice.model.dto.response;

import java.util.List;

public record WorkshopJoinApplicationResponseWrapper(
        List<WorkshopJoinApplicationResponse> workshopJoinRequests) {
}
