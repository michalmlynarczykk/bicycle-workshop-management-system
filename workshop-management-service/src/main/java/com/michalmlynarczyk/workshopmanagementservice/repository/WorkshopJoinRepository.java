package com.michalmlynarczyk.workshopmanagementservice.repository;

import com.michalmlynarczyk.workshopmanagementservice.model.entity.WorkshopJoin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkshopJoinRepository extends MongoRepository<WorkshopJoin, String> {

    Optional<WorkshopJoin> findByWorkshopId(final UUID workshopId);

    Optional<WorkshopJoin> findByWorkshopIdAndUserId(final UUID workshopId, final UUID userId);
}
