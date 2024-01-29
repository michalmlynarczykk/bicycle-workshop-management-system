package com.michalmlynarczyk.orderservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private UUID workshopId;

    private Date createdAt;

    private Date collectedAt;

    private OrderStatus status;

    private Client client;

    private Bike bike;

    private List<Service> services;

    private List<Part> parts;

    public enum OrderStatus {
        NEW,
        IN_PROGRESS,
        DONE,
        COLLECTED
    }
}
