package com.michalmlynarczyk.workshopmanagementservice.controller;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.CreateWorkshopRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopResponse;
import com.michalmlynarczyk.workshopmanagementservice.service.WorkshopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WorkshopControllerImpl implements WorkshopController {

    private final WorkshopService workshopService;


    @Override
    public ResponseEntity<WorkshopResponse> createWorkshop(final CreateWorkshopRequest request,
                                                           final CustomAuthenticationPrincipal principal) {
        log.debug("createWorkshop() - enter - request = {} - principal = {}", request, principal);
        final WorkshopResponse response = workshopService.createWorkshop(request, principal);
        log.debug("createWorkshop() - exit - response = {}", response);
        return ResponseEntity.ok(response);
    }
}
