package com.b2w.apistarwars.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.b2w.apistarwars.client.StarWarsApiTemplate;
import com.b2w.apistarwars.models.Planet;
import com.b2w.apistarwars.models.PlanetApiStarWars;
import com.b2w.apistarwars.models.PlanetResponse;
import com.b2w.apistarwars.services.PlanetService;
import com.b2w.apistarwars.util.URLEncoding;

@RestController
@RequestMapping(value="/planets")
public class PlanetController {

	@Autowired
	private PlanetService service;
	
	@Autowired
	private StarWarsApiTemplate swapi;
	

	private List<PlanetApiStarWars> result = new ArrayList<PlanetApiStarWars>();  

	@PostMapping
	public ResponseEntity<Void> insertPlanets(@RequestBody Planet planeta){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.insert(planeta).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<PlanetResponse>> findAll(){
		return ResponseEntity.ok().body(insertAppears(service.findAll()));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<PlanetResponse> findById(@PathVariable("id") String id){
		Planet planeta = service.findById(id);
		this.result =  callForAppears(this.result);
		return ResponseEntity.ok().body(new PlanetResponse(planeta.getId(),planeta.getName(),planeta.getClimate(),planeta.getTerrain(),findAppears(result, planeta)));
	}
	
	@GetMapping(value="/findName")
	public ResponseEntity<List<PlanetResponse>> findByName(@RequestParam(value="name", defaultValue="") String name){
		List<PlanetResponse> resposta = new ArrayList<>();
		this.result =  callForAppears(this.result);
		for(Planet x: service.findByName(URLEncoding.decodeParam(name)) ) {
			resposta.add(new PlanetResponse(x.getId(),x.getName(),x.getClimate(),x.getTerrain(),findAppears(result,x)));
		}
		return ResponseEntity.ok().body(resposta);
	}


	public List<PlanetResponse> insertAppears(List<Planet> planetas) {
		List<PlanetResponse> resposta = new ArrayList<>();
		this.result =  callForAppears(this.result);
		for(Planet x: planetas ) {
			resposta.add(new PlanetResponse(x.getId(),x.getName(),x.getClimate(),x.getTerrain(),findAppears(result,x)));
		}
		return resposta;
	}
	private int findAppears(List<PlanetApiStarWars> result,Planet planet) {
		for(PlanetApiStarWars y: result ) {
			if(planet.getName().equals(y.getName())) {
				return y.getFilms().size();
			}
		}	
		return 0;
	}
	 
	private List<PlanetApiStarWars> callForAppears(List<PlanetApiStarWars> result) {
		return result = swapi.returnAppears().getBody().getResults(); 
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletePlanet(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
