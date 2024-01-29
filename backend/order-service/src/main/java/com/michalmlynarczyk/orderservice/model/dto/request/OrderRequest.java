package com.michalmlynarczyk.orderservice.model.dto.request;

import com.michalmlynarczyk.orderservice.model.dto.BikeDto;
import com.michalmlynarczyk.orderservice.model.dto.ClientDto;
import com.michalmlynarczyk.orderservice.model.dto.PartDto;
import com.michalmlynarczyk.orderservice.model.dto.ServiceDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
        @NotNull
        @Valid
        ClientDto client,
        @NotNull
        @Valid
        BikeDto bike,
        @NotNull
        @Valid
        List<ServiceDto> services,
        List<PartDto> parts
) {
}
