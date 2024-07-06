package com.microservice.parent.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.microservice.parent.model.TaxInfo;

@Repository
public interface TaxInfoRepository extends MongoRepository<TaxInfo,String> {

}
