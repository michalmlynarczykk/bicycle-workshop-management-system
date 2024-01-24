package com.michalmlynarczyk.orderservice.repository;


import com.michalmlynarczyk.orderservice.model.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
