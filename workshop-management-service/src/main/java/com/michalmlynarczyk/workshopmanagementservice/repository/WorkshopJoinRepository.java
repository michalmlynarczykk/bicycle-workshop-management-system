package com.michalmlynarczyk.workshopmanagementservice.repository;

import com.michalmlynarczyk.workshopmanagementservice.model.entity.WorkshopJoinApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkshopJoinRepository extends MongoRepository<WorkshopJoinApplication, String> {

    Optional<WorkshopJoinApplication> findByIdAndWorkshopId(final String id, final UUID workshopId);

    Optional<WorkshopJoinApplication> findByWorkshopIdAndUserId(final UUID workshopId, final UUID userId);

    List<WorkshopJoinApplication> findAllByWorkshopId(final UUID workshopId);
}
