package com.michalmlynarczyk.orderservice.mapper;

import com.michalmlynarczyk.orderservice.model.dto.ServiceDto;
import com.michalmlynarczyk.orderservice.model.entity.Service;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceMapper {

    public static Service toEntity(final ServiceDto serviceDto) {
        if (serviceDto == null) {
            return null;
        }
        return new Service(
                serviceDto.name(),
                serviceDto.description(),
                serviceDto.price(),
                serviceDto.quantity()
        );
    }


    public static List<Service> toEntity(final List<ServiceDto> services) {
        if (services == null) {
            return null;
        }
        return services
                .stream()
                .map(ServiceMapper::toEntity)
                .toList();
    }


    public static ServiceDto toDto(final Service service) {
        if (service == null) {
            return null;
        }
        return new ServiceDto(
                service.getName(),
                service.getDescription(),
                service.getPrice(),
                service.getQuantity()
        );
    }


    public static List<ServiceDto> toDto(final List<Service> services) {
        if (services == null) {
            return null;
        }
        return services
                .stream()
                .map(ServiceMapper::toDto)
                .toList();
    }
}
