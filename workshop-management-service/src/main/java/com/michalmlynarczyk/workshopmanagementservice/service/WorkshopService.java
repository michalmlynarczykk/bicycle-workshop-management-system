package com.michalmlynarczyk.workshopmanagementservice.service;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.CreateWorkshopRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopResponse;

public interface WorkshopService {

    WorkshopResponse createWorkshop(final CreateWorkshopRequest workshopDto,
                                    final CustomAuthenticationPrincipal principal);
}
