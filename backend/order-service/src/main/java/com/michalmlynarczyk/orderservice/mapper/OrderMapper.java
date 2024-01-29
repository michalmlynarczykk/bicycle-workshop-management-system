package com.michalmlynarczyk.orderservice.mapper;

import com.michalmlynarczyk.orderservice.model.dto.request.OrderRequest;
import com.michalmlynarczyk.orderservice.model.dto.response.OrderDetailsResponse;
import com.michalmlynarczyk.orderservice.model.dto.response.OrderResponse;
import com.michalmlynarczyk.orderservice.model.dto.response.OrdersResponseWrapper;
import com.michalmlynarczyk.orderservice.model.entity.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {
    public static Order toEntity(final OrderRequest order, final UUID workshopId) {
        if (order == null || workshopId == null) {
            return null;
        }


        return new Order(
                null,
                workshopId,
                Date.from(Instant.now()),
                null,
                Order.OrderStatus.NEW,
                ClientMapper.toEntity(order.client()),
                BikeMapper.toEntity(order.bike()),
                ServiceMapper.toEntity(order.services()),
                PartMapper.toEntity(order.parts()));
    }


    public static OrderDetailsResponse toDetailsResponse(final Order order) {
        if (order == null) {
            return null;
        }

        return new OrderDetailsResponse(
                order.getId(),
                order.getCreatedAt(),
                order.getCollectedAt(),
                order.getStatus(),
                ClientMapper.toDto(order.getClient()),
                BikeMapper.toDto(order.getBike()),
                ServiceMapper.toDto(order.getServices()),
                PartMapper.toDto(order.getParts()));

    }


    public static OrderResponse toOrderResponse(final Order order) {
        if (order == null) {
            return null;
        }

        return new OrderResponse(
                order.getId(),
                order.getCreatedAt(),
                order.getCollectedAt(),
                order.getStatus(),
                ClientMapper.toDto(order.getClient()),
                BikeMapper.toDto(order.getBike()));
    }


    public static void updateEntity(final Order order, final OrderRequest request) {
        if (order == null || request == null) {
            return;
        }

        order.setClient(ClientMapper.toEntity(request.client()));
        order.setBike(BikeMapper.toEntity(request.bike()));
        order.setServices(ServiceMapper.toEntity(request.services()));
        order.setParts(PartMapper.toEntity(request.parts()));
    }


    public static OrdersResponseWrapper toOrdersResponseWrapper(final List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new OrdersResponseWrapper(List.of());
        }
        final List<OrderResponse> orderResponses = orders
                .stream()
                .map(OrderMapper::toOrderResponse)
                .toList();
        return new OrdersResponseWrapper(orderResponses);
    }
}
