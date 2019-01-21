package com.b2w.apistarwars.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2w.apistarwars.exception.BadRequest;
import com.b2w.apistarwars.exception.ObjectNotFoundException;
import com.b2w.apistarwars.models.Planet;
import com.b2w.apistarwars.models.StarWarsApiConstants;
import com.b2w.apistarwars.repository.PlanetRepository;

@Service
public class PlanetService {

	
	private PlanetRepository planetRepository;
	
	@Autowired
	public PlanetService(PlanetRepository planetRepository) {
		super();
		this.planetRepository = planetRepository;
	}

	public Planet insert(Planet planet) {
		checkContains(planet);
		planet.setId(null);
		return planetRepository.save(planet);
	}
	 
	public List<Planet> findAll(){
		return planetRepository.findAll();
	}
	
	public Planet findById(String id) {
		Optional<Planet> planetObj = planetRepository.findById(id);
		return  planetObj.orElseThrow(() -> new ObjectNotFoundException(StarWarsApiConstants.ID_NOT_FOUND));
	}
	
	public List<Planet> findByName(String name){
		return planetRepository.findByNameContaining(name);
	}
	
	public void delete(String id) {
		planetRepository.delete(findById(id));
	}
	
	public Planet buildId(Planet obj) {
		Planet id = planetRepository.save(new Planet());     
		obj.setId(id.getId());
		return obj;
	}
	
	public Planet checkContains(Planet obj) {
		try {	
			if(obj.getName().isEmpty() || obj.getName().equals(null)) {
				throw new BadRequest(StarWarsApiConstants.NAME_EMPTY);
			}
			if(obj.getClimate().isEmpty()|| obj.getClimate().equals(null)) {
				throw new BadRequest(StarWarsApiConstants.CLIMATE_EMPTY);
			}
			if(obj.getTerrain().isEmpty()|| obj.getTerrain().equals(null)) {
				throw new BadRequest(StarWarsApiConstants.TERRAIN_EMPTY);
			}
		}catch(Exception e) {
			
			throw new BadRequest(StarWarsApiConstants.NULL_OBJ);
		}
		return obj;
		
	}
}
