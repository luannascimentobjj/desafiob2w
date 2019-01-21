package com.b2w.apistarwars.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.b2w.apistarwars.models.Planet;


public interface PlanetRepository extends MongoRepository<Planet, String> {

	List<Planet> findByNameContaining(String name);
	
}
 