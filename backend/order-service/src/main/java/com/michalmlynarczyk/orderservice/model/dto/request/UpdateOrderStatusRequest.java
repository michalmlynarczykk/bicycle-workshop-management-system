package com.michalmlynarczyk.orderservice.model.dto.request;

import com.michalmlynarczyk.orderservice.model.entity.Order;
import jakarta.validation.constraints.NotNull;

public record UpdateOrderStatusRequest(
        @NotNull
        Order.OrderStatus status) {
}
