package com.michalmlynarczyk.workshopmanagementservice.controller;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.WorkshopJoinApplicationRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinApplicationResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinApplicationResponseWrapper;
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
    public ResponseEntity<WorkshopJoinApplicationResponse> joinWorkshop(final WorkshopJoinApplicationRequest request,
                                                                        final CustomAuthenticationPrincipal principal) {
        log.debug("joinWorkshop() - enter - request = {} - principal = {}", request, principal);
        final WorkshopJoinApplicationResponse response = workshopJoinService.joinWorkshop(request, principal.userId());
        log.debug("joinWorkshop() - exit - response = {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Override
    public ResponseEntity<WorkshopJoinApplicationResponseWrapper> getAllWorkshopJoinApplications(final CustomAuthenticationPrincipal principal) {
        log.debug("getAllWorkshopJoinApplications() - enter - principal = {}", principal);
        final WorkshopJoinApplicationResponseWrapper response = workshopJoinService.getAllWorkshopJoinApplications(principal.workshopId());
        log.debug("getAllWorkshopJoinApplications() - exit - response = {}", response);
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<Void> approveOrRejectJoinApplication(final String joinRequestId,
                                                               final boolean approved,
                                                               final CustomAuthenticationPrincipal principal) {
        log.debug("approveOrRejectJoinApplication() - enter - joinRequestId = {} - approved = {} - principal = {}",
                joinRequestId, approved, principal);

        workshopJoinService.approveOrRejectWorkshopJoinApplication(joinRequestId, approved, principal.workshopId());
        return ResponseEntity.noContent().build();
    }
}
