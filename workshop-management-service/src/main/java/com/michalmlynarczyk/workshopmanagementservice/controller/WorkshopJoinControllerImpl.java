package com.michalmlynarczyk.workshopmanagementservice.controller;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.WorkshopJoinRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinResponseWrapper;
import com.michalmlynarczyk.workshopmanagementservice.service.WorkshopJoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WorkshopJoinControllerImpl implements WorkshopJoinController {

    private final WorkshopJoinService workshopJoinService;


    @Override
    public ResponseEntity<WorkshopJoinResponse> joinWorkshop(final WorkshopJoinRequest request,
                                                             final CustomAuthenticationPrincipal principal) {
        log.debug("joinWorkshop() - enter - request = {} - principal = {}", request, principal);
        final WorkshopJoinResponse response = workshopJoinService.joinWorkshop(request, principal.userId());
        log.debug("joinWorkshop() - exit - response = {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Override
    public ResponseEntity<WorkshopJoinResponseWrapper> getAllWorkshopJoinRequests(final CustomAuthenticationPrincipal principal) {
        log.debug("getAllWorkshopJoinRequests() - enter - principal = {}", principal);
        final WorkshopJoinResponseWrapper response = workshopJoinService.getAllWorkshopJoinRequests(principal.workshopId());
        log.debug("getAllWorkshopJoinRequests() - exit - response = {}", response);
        return ResponseEntity.ok(response);
    }
}
