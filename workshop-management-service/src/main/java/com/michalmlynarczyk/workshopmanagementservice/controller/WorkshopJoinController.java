package com.michalmlynarczyk.workshopmanagementservice.controller;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.WorkshopJoinRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinResponseWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Workshop Join Controller", description = "Endpoints for workshop join requests management")
@RequestMapping("/external/v1/workshops/join-requests")
public interface WorkshopJoinController {

    @PostMapping
    @PreAuthorize("hasAuthority('WORKSHOP_JOIN_WORKSHOP')")
    ResponseEntity<WorkshopJoinResponse> joinWorkshop(@RequestBody @Valid final WorkshopJoinRequest request,
                                                      @AuthenticationPrincipal final CustomAuthenticationPrincipal principal);


    @PostMapping
    @PreAuthorize("hasAuthority('WORKSHOP_WORKSHOP_JOIN_REQUESTS_GET_ALL')")
    ResponseEntity<WorkshopJoinResponseWrapper> getAllWorkshopJoinRequests(@AuthenticationPrincipal final CustomAuthenticationPrincipal principal);
}
