package com.michalmlynarczyk.orderservice.mapper;

import com.michalmlynarczyk.orderservice.model.dto.PartDto;
import com.michalmlynarczyk.orderservice.model.dto.ServiceDto;
import com.michalmlynarczyk.orderservice.model.dto.request.OrderRequest;
import com.michalmlynarczyk.orderservice.model.dto.response.OrderDetailsResponse;
import com.michalmlynarczyk.orderservice.model.entity.Order;
import com.michalmlynarczyk.orderservice.model.entity.Part;
import com.michalmlynarczyk.orderservice.model.entity.Service;
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

        final List<Part> parts = order.parts()
                .stream()
                .map(PartMapper::toEntity).toList();
        final List<Service> services = order.services()
                .stream()
                .map(ServiceMapper::toEntity)
                .toList();


        return new Order(
                null,
                workshopId,
                Date.from(Instant.now()),
                null,
                Order.OrderStatus.NEW,
                ClientMapper.toEntity(order.client()),
                BikeMapper.toEntity(order.bike()),
                services,
                parts);
    }


    public static OrderDetailsResponse toDetailsResponse(final Order order) {
        if (order == null) {
            return null;
        }

        final List<PartDto> parts = order.getParts()
                .stream()
                .map(PartMapper::toDto).toList();
        final List<ServiceDto> services = order.getServices()
                .stream()
                .map(ServiceMapper::toDto)
                .toList();

        return new OrderDetailsResponse(
                order.getId(),
                order.getCreatedAt(),
                order.getCollectedAt(),
                order.getStatus(),
                ClientMapper.toDto(order.getClient()),
                BikeMapper.toDto(order.getBike()),
                services,
                parts);

    }
}
