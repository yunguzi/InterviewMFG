package com.date;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Good;

public interface GoodRepository extends MongoRepository<Good, String> {
	
	public Good findByName(String name);
}