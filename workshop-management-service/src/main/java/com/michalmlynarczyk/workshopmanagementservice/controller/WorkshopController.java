package com.michalmlynarczyk.workshopmanagementservice.controller;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.CreateWorkshopRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Workshop Controller", description = "Endpoints for workshop management")
@RequestMapping("/external/v1/workshops")
public interface WorkshopController {

    @PostMapping
    @PreAuthorize("hasAuthority('WORKSHOP_CREATE_WORKSHOP')")
    ResponseEntity<WorkshopResponse> createWorkshop(@RequestBody @Valid final CreateWorkshopRequest request,
                                                    @AuthenticationPrincipal final CustomAuthenticationPrincipal principal);

    @GetMapping("/assigned")
    @PreAuthorize("hasAuthority('WORKSHOP_GET_ASSIGNED_WORKSHOP')")
    ResponseEntity<WorkshopResponse> getAssignedWorkshop(@AuthenticationPrincipal final CustomAuthenticationPrincipal principal);
}
