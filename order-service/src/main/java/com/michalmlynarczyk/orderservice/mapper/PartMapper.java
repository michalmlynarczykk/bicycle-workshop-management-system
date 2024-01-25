package com.michalmlynarczyk.orderservice.mapper;

import com.michalmlynarczyk.orderservice.model.dto.PartDto;
import com.michalmlynarczyk.orderservice.model.entity.Part;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

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


    public static List<Part> toEntity(final List<PartDto> parts) {
        if (parts == null) {
            return null;
        }

        return parts
                .stream()
                .map(PartMapper::toEntity)
                .toList();
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


    public static List<PartDto> toDto(final List<Part> parts) {
        if (parts == null) {
            return null;
        }

        return parts
                .stream()
                .map(PartMapper::toDto)
                .toList();
    }

}
