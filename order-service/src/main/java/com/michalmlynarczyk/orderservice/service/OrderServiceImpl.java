package com.michalmlynarczyk.orderservice.service;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.orderservice.exception.OrderNotFoundException;
import com.michalmlynarczyk.orderservice.mapper.OrderMapper;
import com.michalmlynarczyk.orderservice.model.dto.request.OrderRequest;
import com.michalmlynarczyk.orderservice.model.dto.response.OrderDetailsResponse;
import com.michalmlynarczyk.orderservice.model.entity.Order;
import com.michalmlynarczyk.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public OrderDetailsResponse createOrder(final OrderRequest request, final CustomAuthenticationPrincipal principal) {
        log.trace("createOrder() - enter - request = {} - principal = {}", request, principal);

        final Order order = OrderMapper.toEntity(request, principal.workshopId());
        final Order savedOrder = orderRepository.save(order);
        log.debug("createOrder() - savedOrder = {}", savedOrder);
        return OrderMapper.toDetailsResponse(savedOrder);

    }


    @Override
    public OrderDetailsResponse updateOrder(final String orderId, final OrderRequest request, final CustomAuthenticationPrincipal principal) {
        log.trace("updateOrder() - enter - orderId = {} - request = {} - principal = {}", orderId, request, principal);
        final Order order = getOrderOrThrowException(orderId);
        OrderMapper.updateEntity(order, request);
        orderRepository.save(order);
        log.debug("updateOrder() - order updated = {}", order);
        return OrderMapper.toDetailsResponse(order);
    }


    private Order getOrderOrThrowException(final String orderId) {
        log.trace("getOrderOrThrowException() - enter - orderId = {}", orderId);
        final Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found - orderId = {0}", orderId));
        log.debug("getOrderOrThrowException() - exit - order = {}", order);
        return order;
    }
}
