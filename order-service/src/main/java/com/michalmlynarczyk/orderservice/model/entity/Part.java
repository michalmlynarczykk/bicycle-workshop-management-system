package com.michalmlynarczyk.orderservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Part {

    private String name;

    private String description;

    private Integer price;

    private Integer quantity;
}
