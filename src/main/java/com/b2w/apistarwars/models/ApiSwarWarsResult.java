package com.b2w.apistarwars.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiSwarWarsResult {

	private List<PlanetApiStarWars> results;

	
	public ApiSwarWarsResult() {
	}


	public ApiSwarWarsResult(List<PlanetApiStarWars> results, String name) {
		this.results = results;
	}


	public List<PlanetApiStarWars> getResults() {
		return results;
	}

	public void setResults(List<PlanetApiStarWars> results) {
		this.results = results;
	}
	 
	
}
