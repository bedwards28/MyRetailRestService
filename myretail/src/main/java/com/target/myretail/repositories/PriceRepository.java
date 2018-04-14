package com.target.myretail.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.target.myretail.model.Price;

@Repository
public interface PriceRepository extends MongoRepository<Price, String> {
	
//	@Query("{'productId' : ?0}")
	public Price findByProductId(String productId);

}
