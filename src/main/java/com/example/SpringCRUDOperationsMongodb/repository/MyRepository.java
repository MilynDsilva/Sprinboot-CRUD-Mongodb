package com.example.SpringCRUDOperationsMongodb.repository;

import com.example.SpringCRUDOperationsMongodb.model.CarPojo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyRepository extends MongoRepository<CarPojo,Integer> {
}
