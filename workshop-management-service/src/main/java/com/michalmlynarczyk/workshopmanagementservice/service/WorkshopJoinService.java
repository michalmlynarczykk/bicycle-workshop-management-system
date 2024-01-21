package com.michalmlynarczyk.workshopmanagementservice.service;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.WorkshopJoinRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinResponse;

import java.util.UUID;

public interface WorkshopJoinService {

    WorkshopJoinResponse joinWorkshop(final WorkshopJoinRequest workshopJoinRequest, final UUID userId);

}
