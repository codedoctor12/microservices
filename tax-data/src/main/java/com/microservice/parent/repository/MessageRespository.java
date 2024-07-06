package com.microservice.parent.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.parent.model.Messages;

@Repository
public interface MessageRespository extends MongoRepository<Messages,String> {

}
