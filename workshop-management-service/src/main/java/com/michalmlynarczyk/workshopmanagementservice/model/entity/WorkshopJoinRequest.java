package com.michalmlynarczyk.workshopmanagementservice.model.entity;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.JoinRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "workshop_join_requests")
public class WorkshopJoinRequest {

    @Id
    private String id;

    private OffsetDateTime createdAt;

    private OffsetDateTime decidedAt;

    private String workshopId;

    private String userId;

    private JoinRequestStatus status;
}
