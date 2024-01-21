package com.michalmlynarczyk.workshopmanagementservice.controller;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.request.WorkshopJoinRequest;
import com.michalmlynarczyk.workshopmanagementservice.model.dto.response.WorkshopJoinResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Workshop Join Controller", description = "Endpoints for workshop join requests management")
@RequestMapping("/external/v1/workshops/join")
public interface WorkshopJoinController {

    @PostMapping
    @PreAuthorize("hasAuthority('WORKSHOP_JOIN_WORKSHOP')")
    ResponseEntity<WorkshopJoinResponse> createWorkshop(@RequestBody @Valid final WorkshopJoinRequest request,
                                                        @AuthenticationPrincipal final CustomAuthenticationPrincipal principal);
}
