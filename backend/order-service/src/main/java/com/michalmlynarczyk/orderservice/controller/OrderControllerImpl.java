package com.michalmlynarczyk.orderservice.controller;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.orderservice.model.dto.request.OrderRequest;
import com.michalmlynarczyk.orderservice.model.dto.response.OrderDetailsResponse;
import com.michalmlynarczyk.orderservice.model.dto.response.OrdersResponseWrapper;
import com.michalmlynarczyk.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;


    @Override
    public ResponseEntity<OrderDetailsResponse> createOrder(final OrderRequest request,
                                                            final CustomAuthenticationPrincipal principal) {
        log.debug("createOrder() - enter - request = {} - principal = {}", request, principal);
        final OrderDetailsResponse response = orderService.createOrder(request, principal);
        log.debug("createOrder() - exit - response = {}", response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @Override
    public ResponseEntity<OrderDetailsResponse> updateOrder(final String orderId,
                                                            final OrderRequest request,
                                                            final CustomAuthenticationPrincipal principal) {
        log.debug("updateOrder() - enter - orderId = {} - request = {} - principal = {}", orderId, request, principal);
        final OrderDetailsResponse response = orderService.updateOrder(orderId, request, principal);
        log.debug("updateOrder() - exit - response = {}", response);
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<OrdersResponseWrapper> getAllOrders(final CustomAuthenticationPrincipal principal) {
        log.debug("getAllOrders() - enter - principal = {}", principal);
        final OrdersResponseWrapper response = orderService.getAllOrders(principal);
        log.debug("getAllOrders() - exit - response = {}", response);
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<OrderDetailsResponse> getOrderDetails(final String orderId, final CustomAuthenticationPrincipal principal) {
        log.debug("getOrderDetails() - enter - orderId = {} - principal = {}", orderId, principal);
        final OrderDetailsResponse response = orderService.getOrderDetails(orderId, principal);
        log.debug("getOrderDetails() - exit - response = {}", response);
        return ResponseEntity.ok(response);
    }

}
