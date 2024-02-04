package com.michalmlynarczyk.orderservice.service;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.orderservice.exception.OrderNotFoundException;
import com.michalmlynarczyk.orderservice.mapper.OrderMapper;
import com.michalmlynarczyk.orderservice.model.dto.request.OrderRequest;
import com.michalmlynarczyk.orderservice.model.dto.request.UpdateOrderStatusRequest;
import com.michalmlynarczyk.orderservice.model.dto.response.OrderDetailsResponse;
import com.michalmlynarczyk.orderservice.model.dto.response.OrdersResponseWrapper;
import com.michalmlynarczyk.orderservice.model.entity.Order;
import com.michalmlynarczyk.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;


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
        final Order order = getOrderOrThrowException(orderId, principal.workshopId());
        OrderMapper.updateEntity(order, request);
        orderRepository.save(order);
        log.debug("updateOrder() - order updated = {}", order);
        return OrderMapper.toDetailsResponse(order);
    }


    @Override
    public OrdersResponseWrapper getAllOrders(final CustomAuthenticationPrincipal principal) {
        log.trace("getAllOrders() - enter - principal = {}", principal);
        final List<Order> orders = orderRepository.findAllByWorkshopId(principal.workshopId());

        return OrderMapper.toOrdersResponseWrapper(orders);
    }


    @Override
    public OrderDetailsResponse getOrderDetails(final String orderId, final CustomAuthenticationPrincipal principal) {
        log.trace("getOrderDetails() - enter - orderId = {} - principal = {}", orderId, principal);
        final Order order = getOrderOrThrowException(orderId, principal.workshopId());
        return OrderMapper.toDetailsResponse(order);
    }


    @Override
    public void updateOrderStatus(final String orderId, final UpdateOrderStatusRequest request, final CustomAuthenticationPrincipal principal) {
        log.trace("updateOrderStatus() - enter - orderId = {} - request = {} - principal = {}", orderId, request, principal);
        final Order order = getOrderOrThrowException(orderId, principal.workshopId());
        order.updateOrderStatus(request.status());
        orderRepository.save(order);
        log.debug("updateOrderStatus() - order updated = {}", order);
    }


    private Order getOrderOrThrowException(final String orderId, final UUID workshopId) {
        log.trace("getOrderOrThrowException() - enter - orderId = {} - workshopId = {}", orderId, workshopId);
        final Order order = orderRepository.findByIdAndWorkshopId(orderId, workshopId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found - orderId = {0}", orderId));
        log.debug("getOrderOrThrowException() - exit - order = {}", order);
        return order;
    }
}
