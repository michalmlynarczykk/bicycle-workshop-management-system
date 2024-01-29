package com.michalmlynarczyk.orderservice.model.dto.response;

import com.michalmlynarczyk.orderservice.model.dto.BikeDto;
import com.michalmlynarczyk.orderservice.model.dto.ClientDto;
import com.michalmlynarczyk.orderservice.model.dto.PartDto;
import com.michalmlynarczyk.orderservice.model.dto.ServiceDto;
import com.michalmlynarczyk.orderservice.model.entity.Order;

import java.util.Date;
import java.util.List;

public record OrderDetailsResponse(
        String id,
        Date createdAt,
        Date collectedAt,
        Order.OrderStatus status,
        ClientDto client,
        BikeDto bike,
        List<ServiceDto> services,
        List<PartDto> parts) {
}
