package com.michalmlynarczyk.workshopmanagementservice.model.entity;

import com.michalmlynarczyk.workshopmanagementservice.model.dto.ApplicationStatus;
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
@Document(collection = "workshop_join_applications")
public class WorkshopJoinApplication {

    @Id
    private String id;

    private Date createdAt;

    private Date decidedAt;

    private UUID workshopId;

    private UUID userId;

    private ApplicationStatus status;


    public static WorkshopJoinApplication of(final UUID workshopId, final UUID userId) {
        return WorkshopJoinApplication
                .builder()
                .createdAt(Date.from(OffsetDateTime.now().toInstant()))
                .userId(userId)
                .workshopId(workshopId)
                .status(ApplicationStatus.PENDING)
                .build();
    }


    public void approve() {
        this.status = ApplicationStatus.APPROVED;
        this.decidedAt = Date.from(OffsetDateTime.now().toInstant());
    }


    public void reject() {
        this.status = ApplicationStatus.REJECTED;
        this.decidedAt = Date.from(OffsetDateTime.now().toInstant());
    }
}
