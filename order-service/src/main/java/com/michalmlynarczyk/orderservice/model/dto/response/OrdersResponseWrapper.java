package com.michalmlynarczyk.orderservice.model.dto.response;

import java.util.List;

public record OrdersResponseWrapper(
        List<OrderResponse> orders) {
}
