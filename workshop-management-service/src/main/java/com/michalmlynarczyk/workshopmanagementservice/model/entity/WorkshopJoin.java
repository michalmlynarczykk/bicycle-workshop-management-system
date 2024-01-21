package com.michalmlynarczyk.workshopmanagementservice.model.entity;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.JoinRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "workshop_join_requests")
public class WorkshopJoin {

    @Id
    private String id;

    private Date createdAt;

    private Date decidedAt;

    private UUID workshopId;

    private UUID userId;

    private JoinRequestStatus status;


    public static WorkshopJoin of(final UUID workshopId, final UUID userId) {
        return WorkshopJoin
                .builder()
                .createdAt(Date.from(OffsetDateTime.now().toInstant()))
                .workshopId(workshopId)
                .status(JoinRequestStatus.PENDING)
                .build();
    }
}
