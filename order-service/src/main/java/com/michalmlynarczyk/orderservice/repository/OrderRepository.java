package com.michalmlynarczyk.orderservice.repository;


import com.michalmlynarczyk.orderservice.model.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<Order> findByIdAndWorkshopId(final String orderId, final UUID workshopId);

}
