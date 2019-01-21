package com.b2w.apistarwars.models;


public class PlanetResponse {

	private String id;
	private String name;
	private String climate;
	private String terrain;
	private int appears;
	
	public PlanetResponse() {
	}

	
	public PlanetResponse(String id, String name, String climate, String terrain, int appears) {
		this.id = id;
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
		this.appears = appears;
	}
	
	
	public String getId() {
		return id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public int getAppears() {
		return appears;
	}
	public void setAppears(int appears) {
		this.appears = appears;
	}



		
		

}
