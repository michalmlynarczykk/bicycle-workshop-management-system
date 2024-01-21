package com.michalmlynarczyk.workshopmanagementservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "workshops")
public class Workshop {

    @Id
    private UUID id;

    private Date createdAt;

    private UUID ownerId;

    private String name;

    private Address address;

}
