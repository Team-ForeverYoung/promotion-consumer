package com.java.promotionconsumer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.java.promotionconsumer.entity.PromotionResultLog;
@Repository
public interface EventResultRepository extends MongoRepository<PromotionResultLog, String> {
}
