package com.michalmlynarczyk.workshopmanagementservice.repository;

import com.michalmlynarczyk.workshopmanagementservice.model.entity.Workshop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkshopRepository extends MongoRepository<Workshop, UUID> {

    Optional<Workshop> findByOwnerId(final UUID ownerId);
}
