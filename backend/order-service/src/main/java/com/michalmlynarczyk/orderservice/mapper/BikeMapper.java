package com.michalmlynarczyk.orderservice.mapper;

import com.michalmlynarczyk.orderservice.model.dto.BikeDto;
import com.michalmlynarczyk.orderservice.model.entity.Bike;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BikeMapper {

    public static Bike toEntity(final BikeDto bikeDto) {
        if (bikeDto == null) {
            return null;
        }
        return new Bike(
                bikeDto.brand(),
                bikeDto.model(),
                bikeDto.color(),
                bikeDto.frameNumber(),
                bikeDto.productionYear(),
                bikeDto.description()
        );
    }


    public static BikeDto toDto(final Bike bike) {
        if (bike == null) {
            return null;
        }
        return new BikeDto(
                bike.getBrand(),
                bike.getModel(),
                bike.getColor(),
                bike.getFrameNumber(),
                bike.getProductionYear(),
                bike.getDescription()
        );
    }
}
