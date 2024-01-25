package com.michalmlynarczyk.orderservice.service;

import com.michalmlynarczyk.common.model.dto.authentication.CustomAuthenticationPrincipal;
import com.michalmlynarczyk.orderservice.model.dto.request.OrderRequest;
import com.michalmlynarczyk.orderservice.model.dto.response.OrderDetailsResponse;

public interface OrderService {

    OrderDetailsResponse createOrder(final OrderRequest request, final CustomAuthenticationPrincipal principal);

    OrderDetailsResponse updateOrder(final String orderId, final OrderRequest request, final CustomAuthenticationPrincipal principal);
}
