package com.michalmlynarczyk.orderservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bike {

    private String brand;

    private String model;

    private String color;

    private String frameNumber;

    private Integer productionYear;

    private String description;
}
