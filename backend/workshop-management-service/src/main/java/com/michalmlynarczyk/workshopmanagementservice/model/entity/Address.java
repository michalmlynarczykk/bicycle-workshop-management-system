package com.michalmlynarczyk.workshopmanagementservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;

    private String buildingNumber;

    private String apartmentNumber;

    private String city;

    private String zipCode;

}
