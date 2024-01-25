package com.michalmlynarczyk.orderservice.controller;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.orderservice.model.dto.request.OrderRequest;
import com.michalmlynarczyk.orderservice.model.dto.response.OrderDetailsResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

;

@Tag(name = "Order Controller", description = "Endpoints for orders management")
@RequestMapping("/external/v1/orders")
public interface OrderController {

    @PostMapping
    @PreAuthorize("hasAuthority('ORDER_CREATE_ORDER')")
    ResponseEntity<OrderDetailsResponse> createOrder(@RequestBody @Valid final OrderRequest request,
                                                     @AuthenticationPrincipal final CustomAuthenticationPrincipal principal);

    @PutMapping("/{orderId}")
    @PreAuthorize("hasAuthority('ORDER_UPDATE_ORDER')")
    ResponseEntity<OrderDetailsResponse> updateOrder(@PathVariable(name = "orderId") final String orderId,
                                                     @RequestBody @Valid final OrderRequest request,
                                                     @AuthenticationPrincipal final CustomAuthenticationPrincipal principal);
}
