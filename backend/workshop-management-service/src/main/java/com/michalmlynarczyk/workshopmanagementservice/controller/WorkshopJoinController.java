package com.michalmlynarczyk.workshopmanagementservice.controller;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.WorkshopJoinApplicationRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinApplicationResponse;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinApplicationResponseWrapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Workshop Join Controller", description = "Endpoints for workshop join requests management")
@RequestMapping("/external/v1/workshops/join-requests")
public interface WorkshopJoinController {

    @PostMapping
    @PreAuthorize("hasAuthority('WORKSHOP_JOIN_WORKSHOP')")
    ResponseEntity<WorkshopJoinApplicationResponse> joinWorkshop(@RequestBody @Valid final WorkshopJoinApplicationRequest request,
                                                                 @AuthenticationPrincipal final CustomAuthenticationPrincipal principal);


    @GetMapping
    @PreAuthorize("hasAuthority('WORKSHOP_WORKSHOP_JOIN_REQUESTS_GET_ALL')")
    ResponseEntity<WorkshopJoinApplicationResponseWrapper> getAllWorkshopJoinApplications(@AuthenticationPrincipal final CustomAuthenticationPrincipal principal);

    @PatchMapping("/{joinRequestId}")
    @PreAuthorize("hasAuthority('WORKSHOP_WORKSHOP_JOIN_REQUEST_APPROVE_REJECT')")
    ResponseEntity<Void> approveOrRejectJoinApplication(@PathVariable(name = "joinRequestId") final String joinRequestId,
                                                        @RequestParam(name = "approved", defaultValue = "true") final boolean approved,
                                                        @AuthenticationPrincipal final CustomAuthenticationPrincipal principal);
}
