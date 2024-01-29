package com.michalmlynarczyk.workshopmanagementservice.service;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.WorkshopJoinApplicationRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinApplicationResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinApplicationResponseWrapper;

import java.util.UUID;

public interface WorkshopJoinService {

    WorkshopJoinApplicationResponse joinWorkshop(final WorkshopJoinApplicationRequest workshopJoinApplicationRequest, final UUID userId);

    WorkshopJoinApplicationResponseWrapper getAllWorkshopJoinApplications(final UUID workshopId);

    void approveOrRejectWorkshopJoinApplication(final String joinRequestId, final boolean approved, final UUID workshopId);

}
