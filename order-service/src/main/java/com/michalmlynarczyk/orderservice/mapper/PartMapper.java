package com.michalmlynarczyk.orderservice.mapper;

import com.michalmlynarczyk.orderservice.model.dto.PartDto;
import com.michalmlynarczyk.orderservice.model.entity.Part;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PartMapper {

    public static Part toEntity(final PartDto partDto) {
        if (partDto == null) {
            return null;
        }
        return new Part(
                partDto.name(),
                partDto.description(),
                partDto.price(),
                partDto.quantity()
        );
    }


    public static PartDto toDto(final Part part) {
        if (part == null) {
            return null;
        }
        return new PartDto(
                part.getName(),
                part.getDescription(),
                part.getPrice(),
                part.getQuantity()
        );
    }
}
