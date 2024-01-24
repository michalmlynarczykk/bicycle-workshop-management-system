package com.michalmlynarczyk.orderservice.model.dto.response;

import com.michalmlynarczyk.orderservice.model.dto.BikeDto;
import com.michalmlynarczyk.orderservice.model.dto.ClientDto;
import com.michalmlynarczyk.orderservice.model.entity.Order;

import java.util.Date;

public record OrderResponse(
        String id,
        Date createdAt,
        Date collectedAt,
        Order.OrderStatus status,
        ClientDto client,
        BikeDto bike) {
}
